package com.mayman.kmp.book.presentation.book_list

import com.mayman.kmp.book.domain.model.Book
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

data class BookListState(
    val selectedTabIndex: Int = 0,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchResults: ImmutableList<Book> = persistentListOf(),
    val favourites: ImmutableList<Book> = persistentListOf()
)


sealed interface BookListIntent {
    data class OnTabSelected(val index: Int) : BookListIntent
    data class OnBookClick(val book: Book) : BookListIntent
    data class OnSearchQueryChanged(val query: String) : BookListIntent
}

val books = persistentListOf(Book(
    id = "1",
    name = "Karen McNeil",
    author = "adipiscing",
    description = "saperet",
    imageUrl = "https://picsum.photos/200",
    rating = 2.3
),
    Book(
        id = "2",
        name = "Harry potter",
        author = "Ahmed",
        description = "description tool onaslkdnaksndjkasdjkasdjk",
        imageUrl = "https://picsum.photos/200",
        rating = 3.3
    ),)