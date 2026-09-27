package com.mayman.kmp.book.presentation.book_list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayman.kmp.book.domain.model.Book
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

//
// Created by Mahmoud Ayman Mostafa on 27/09/2026.
//
@Composable
fun BookList(
    books: ImmutableList<Book>,
    onBookClick: (Book) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(books, key = { it.id }) { book ->
            BookListItem(
                book = book,
                onClick = { onBookClick(book) },
                modifier = Modifier.widthIn(max = 700.dp).fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BookListPreview() {
    MaterialTheme {
        BookList(
            books = (1..50).mapIndexed { _, item ->
                Book(
                    id = "$item",
                    name = "Item ${item}",
                    author = "Author $item",
                    description = "Desc $item",
                    imageUrl = "https://picsum.photos/200",
                    rating = item * .32
                )
            }.toImmutableList(),
            onBookClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}