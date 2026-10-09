package com.mayman.kmp.movie.domain.repo

import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.movie.domain.model.Movie
import com.mayman.kmp.core.domain.Result

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//
interface MoviesRepo {
    suspend fun getMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun searchMovie(query: String): Result<List<Movie>, DataError.Remote>
}