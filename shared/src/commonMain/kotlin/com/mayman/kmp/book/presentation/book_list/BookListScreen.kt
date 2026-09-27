package com.mayman.kmp.book.presentation.book_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayman.kmp.book.domain.model.Book
import com.mayman.kmp.book.presentation.book_list.composables.SearchTextField

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

@Composable
fun BookListScreen(onBookClick: (Book) -> Unit) {
    val viewModel: BookListViewModel = BookListViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookListContent(state, viewModel::onIntent)
}

@Composable
private fun BookListContent(
    state: BookListState,
    onIntent: (BookListIntent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            searchQuery = state.searchQuery,
            onSearchQueryChanged = { onIntent(BookListIntent.OnSearchQueryChanged(it)) },
            onSearchImeClick = {
                onIntent(BookListIntent.OnSearchQueryChanged(state.searchQuery))
            },
            modifier = Modifier.widthIn(max = 400.dp).fillMaxWidth().padding(16.dp)
        )

    }
}

@Preview
@Composable
fun PreviewBookListContent() {
    MaterialTheme {
        BookListContent(BookListState()) {}
    }
}