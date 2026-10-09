package com.mayman.kmp.movie.domain.model

//
// Created by Mahmoud Ayman Mostafa on 26/09/2026.
//
data class Movie(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rating: Double
)