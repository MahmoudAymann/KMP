package com.mayman.kmp.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayman.kmp.book.domain.model.Book
import com.mayman.kmp.book.presentation.book_list.composables.AppTabbedPager
import com.mayman.kmp.book.presentation.book_list.composables.BookList
import com.mayman.kmp.book.presentation.book_list.composables.SearchTextField
import com.mayman.kmp.book.presentation.book_list.composables.TabItem
import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.favourites
import kmp.shared.generated.resources.search_hint
import kmp.shared.generated.resources.search_results
import kotlinx.collections.immutable.persistentListOf
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//

@Composable
fun BookListScreen(
    viewModel: BookListViewModel = viewModel { BookListViewModel() },
    onBookClick: (Book) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BookListContent(state, viewModel::onIntent)
}

@Composable
private fun BookListContent(
    state: BookListState,
    onIntent: (BookListIntent) -> Unit
) {
    val searchResultListState = rememberLazyListState()
    LaunchedEffect(state.searchResults) {
        searchResultListState.animateScrollToItem(0)
    }
    val favouriteListState = rememberLazyListState()
    LaunchedEffect(state.favourites) {
        favouriteListState.animateScrollToItem(0)
    }
    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding().background(color = MaterialTheme.colorScheme.primary),
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

        Surface(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(topEnd = 32.dp, topStart = 32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                AppTabbedPager(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    tabs = persistentListOf(
                        TabItem(title = stringResource(Res.string.search_results)),
                        TabItem(title = stringResource(Res.string.favourites))
                    ),
                    selectedIndex = state.selectedTabIndex,
                    onTabSelected = { selectedIndex ->
                        onIntent(BookListIntent.OnTabSelected(selectedIndex))
                    }
                ) { pageIndex ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        when (pageIndex) {
                            0 -> {
                                if (state.isLoading)
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                else {
                                    when {
                                        state.errorMessage != null -> Text(text = state.errorMessage)
                                        state.searchResults.isEmpty() -> Text(
                                            text = "no search results",
                                            textAlign = TextAlign.Center
                                        )

                                        else -> {
                                            BookList(
                                                books = state.searchResults, onBookClick = {
                                                    onIntent(BookListIntent.OnBookClick(it))
                                                }, modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.favourites.isEmpty()) {
                                    Text(
                                        text = "no favourite results",
                                        textAlign = TextAlign.Center
                                    )
                                } else {
                                    BookList(
                                        books = state.favourites, onBookClick = {
                                            onIntent(BookListIntent.OnBookClick(it))
                                        }, modifier = Modifier.fillMaxSize(),
                                        scrollState = favouriteListState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBookListContent() {

    MaterialTheme {
        BookListContent(BookListState(searchResults = books, searchQuery = "kl")) {}
    }
}