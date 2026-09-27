package com.mayman.kmp.book.domain.model

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//
data class Book(
    val id: String,
    val name: String,
    val author: String,
    val description: String,
    val imageUrl: String,
    val rating: Double
)