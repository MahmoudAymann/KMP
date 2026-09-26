package com.mayman.kmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mayman.kmp.book.presentation.book_list.composables.SearchTextField

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//
@Preview
@Composable
fun PreviewSearchTextField() {
    MaterialTheme{
        SearchTextField(
            "",
            onSearchQueryChanged = {},
            onSearchIconClick = {},
            modifier = Modifier,
        )
    }
}