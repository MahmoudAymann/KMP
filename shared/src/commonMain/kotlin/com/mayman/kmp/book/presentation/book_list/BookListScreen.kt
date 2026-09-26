package com.mayman.kmp.book.presentation.book_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayman.kmp.book.domain.model.Book
import org.koin.compose.viewmodel.koinViewModel

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

@Composable
fun BookListScreen(onBookClick: (Book) -> Unit) {
    val viewModel: BookListViewModel = koinViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookListContent(state, viewModel::onIntent)
}

@Composable
private fun BookListContent(
    state: BookListState,
    onIntent: (BookListIntent) -> Unit
) {

}