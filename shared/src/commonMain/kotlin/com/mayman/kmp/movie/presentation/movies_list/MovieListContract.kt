package com.mayman.kmp.movie.presentation.movies_list

import com.mayman.kmp.movie.domain.model.Movie
import com.mayman.kmp.core.presentation.UiText
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

data class BookListState(
    val selectedTabIndex: Int = 0,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val searchResults: ImmutableList<Movie> = persistentListOf(),
    val favourites: ImmutableList<Movie> = persistentListOf()
)


sealed interface BookListIntent {
    data class OnTabSelected(val index: Int) : BookListIntent
    data class OnBookClick(val movie: Movie) : BookListIntent
    data class OnSearchQueryChanged(val query: String) : BookListIntent
}