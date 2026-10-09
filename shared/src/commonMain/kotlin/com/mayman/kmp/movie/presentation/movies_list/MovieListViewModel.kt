package com.mayman.kmp.movie.presentation.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayman.kmp.movie.domain.repo.MoviesRepo
import com.mayman.kmp.core.domain.onError
import com.mayman.kmp.core.domain.onSuccess
import com.mayman.kmp.core.domain.toUiText
import com.mayman.kmp.movie.domain.model.Movie
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//
class MovieListViewModel(
    private val moviesRepo: MoviesRepo
) : ViewModel() {

    private var searchJob: Job? = null
    private var getMoviesJob: Job? = null
    private var cachedMovies = emptyList<Movie>()
    private val _state = MutableStateFlow(BookListState())
    val state: StateFlow<BookListState> = _state.onStart {
        if (cachedMovies.isEmpty()) {
            getMoviesList()
        }
        observeSearchQuery()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    fun onIntent(intent: BookListIntent) {
        when (intent) {
            is BookListIntent.OnTabSelected -> _state.update {
                it.copy(selectedTabIndex = intent.index)
            }

            is BookListIntent.OnBookClick -> {

            }

            is BookListIntent.OnSearchQueryChanged -> {
                _state.update {
                    it.copy(searchQuery = intent.query)
                }
            }
        }
    }


    private fun observeSearchQuery() {
        state.map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500.milliseconds)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        searchJob?.cancel()
                        if (cachedMovies.isEmpty()) {
                            if (getMoviesJob?.isActive != true) {
                                getMoviesList()
                            }
                        } else {
                            _state.update {
                                it.copy(
                                    errorMessage = null,
                                    searchResults = cachedMovies.toImmutableList()
                                )
                            }
                        }
                    }

                    query.length > 2 -> {
                        searchJob?.cancel()
                        searchJob = searchMovies(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getMoviesList() {
        getMoviesJob?.cancel()
        getMoviesJob = viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            moviesRepo.getMovies().onSuccess { movies ->
                cachedMovies = movies
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = movies.toImmutableList()
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        searchResults = persistentListOf(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
        }
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        moviesRepo.searchMovie(query).onSuccess { searchResults ->
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessage = null,
                    searchResults = searchResults.toImmutableList()
                )
            }
        }.onError { error ->
            _state.update {
                it.copy(
                    searchResults = persistentListOf(),
                    isLoading = false,
                    errorMessage = error.toUiText()
                )
            }
        }
    }
}