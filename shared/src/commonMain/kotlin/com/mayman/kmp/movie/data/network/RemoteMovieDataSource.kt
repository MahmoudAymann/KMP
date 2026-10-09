package com.mayman.kmp.movie.data.network

import com.mayman.kmp.movie.data.dto.MoviesResponseDto
import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.core.domain.Result

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//
interface RemoteMovieDataSource {
    suspend fun getMovies(): Result<MoviesResponseDto, DataError.Remote>
    suspend fun searchMovie(query: String): Result<MoviesResponseDto, DataError.Remote>
}