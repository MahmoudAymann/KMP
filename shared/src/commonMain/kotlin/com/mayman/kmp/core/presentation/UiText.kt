package com.mayman.kmp.core.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

//
// Created by Mahmoud Ayman Mostafa on 25/09/2026.
//
sealed interface UiText {
    data class DynamicString(val value: String) : UiText

    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ) : UiText

    @Composable
    fun asString(): String = when (this) {
        is DynamicString -> value
        is StringResourceId -> stringResource(id, *args)
    }
}