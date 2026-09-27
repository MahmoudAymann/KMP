package com.mayman.kmp.book.presentation.book_list.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.search_hint
import org.jetbrains.compose.resources.stringResource

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//
@Composable
fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchImeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.minimumInteractiveComponentSize(),
        shape = CircleShape,
        value = searchQuery,
        colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary, unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary),
        placeholder = {
            Text(text = stringResource(Res.string.search_hint), color = MaterialTheme.colorScheme.onPrimary)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
        },
        trailingIcon = {
            AnimatedVisibility(visible = searchQuery.isNotBlank()) {
                IconButton(onClick = {
                    onSearchQueryChanged("")
                }) {
                    Icon(imageVector = Icons.Rounded.Close, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        },
        singleLine = true,
        onValueChange = onSearchQueryChanged,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchImeClick() }),
    )


}

@Preview
@Composable
private fun PreviewSearchTextField() {
    MaterialTheme {
        SearchTextField(
            modifier = Modifier.fillMaxWidth(),
            searchQuery = "sss",
            onSearchQueryChanged = {},
            onSearchImeClick = {},
        )
    }
}