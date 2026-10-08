package com.mayman.kmp.book.presentation.book_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//
class BookListViewModel : ViewModel() {


    private val _state = MutableStateFlow(BookListState(searchResults = books))
    val state : StateFlow<BookListState> = _state.asStateFlow()

    fun onIntent(intent: BookListIntent) {
        when(intent){
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
}