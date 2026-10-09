package com.mayman.kmp.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//

@Serializable
data class MovieDto(
    @SerialName("id") val id: Long?,
    @SerialName("original_title") val title: String?,
    @SerialName("overview") val description: String?,
    @SerialName("vote_average") val rating: Double?,
    @SerialName("poster_path") val coverImage: String?,
    @SerialName("release_date") val releaseDate: String?,
)
