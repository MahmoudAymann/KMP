package com.mayman.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.mayman.kmp.book.presentation.book_list.BookListScreen

@Composable
fun App() {
    MaterialTheme {
        BookListScreen(){}
    }
}