package com.mayman.kmp.movie.data.mappers

import com.mayman.kmp.core.data.ApiService
import com.mayman.kmp.movie.data.dto.MovieDto
import com.mayman.kmp.movie.domain.model.Movie

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//

fun MovieDto.toBook(): Movie {
    return Movie(
        id = id ?: -1L,
        title = title.orEmpty(),
        description = description.orEmpty(),
        rating = rating ?: 0.0,
        imageUrl = ApiService.IMAGE_BASE_URL.plus(coverImage.orEmpty())
    )
}