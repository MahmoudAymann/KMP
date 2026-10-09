package com.mayman.kmp.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//
@Serializable
data class MoviesResponseDto(
    @SerialName("page") val page: Int?,
    @SerialName("total_pages") val totalPages: Int?,
    @SerialName("results") val results: List<MovieDto?>?
)
