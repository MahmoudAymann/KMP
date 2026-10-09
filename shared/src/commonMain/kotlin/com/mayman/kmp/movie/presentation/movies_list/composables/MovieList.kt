package com.mayman.kmp.movie.presentation.movies_list.composables

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
import com.mayman.kmp.movie.domain.model.Movie
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

//
// Created by Mahmoud Ayman Mostafa on 27/09/2026.
//
@Composable
fun BookList(
    movies: ImmutableList<Movie>,
    onBookClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState()
) {
    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(movies, key = { it.id }) { book ->
            BookListItem(
                movie = book,
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
            movies = (1..50).mapIndexed { _, item ->
                Movie(
                    id = item.toLong(),
                    title = "Item $item",
                    description = "Author $item",
                    imageUrl = "https://picsum.photos/200",
                    rating = item * .32
                )
            }.toImmutableList(),
            onBookClick = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}