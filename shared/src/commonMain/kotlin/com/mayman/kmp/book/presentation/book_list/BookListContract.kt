package com.mayman.kmp.book.presentation.book_list

import com.mayman.kmp.book.domain.model.Book

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

data class BookListState(val selectedTabIndex: Int = 0, val searchQuery: String = "")


sealed interface BookListIntent {
    data class OnTabSelected(val index: Int) : BookListIntent
    data class OnBookClick(val book: Book) : BookListIntent
    data class OnSearchQueryChanged(val query: String) : BookListIntent
}