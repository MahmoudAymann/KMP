package com.mayman.kmp.book.presentation.book_list.composables

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//
@Composable
fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(value = searchQuery, onValueChange = onSearchQueryChanged)


}