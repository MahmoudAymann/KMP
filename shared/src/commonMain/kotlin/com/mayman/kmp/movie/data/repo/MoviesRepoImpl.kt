package com.mayman.kmp.movie.data.repo

import com.mayman.kmp.movie.data.mappers.toBook
import com.mayman.kmp.movie.data.network.RemoteMovieDataSource
import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.movie.domain.model.Movie
import com.mayman.kmp.movie.domain.repo.MoviesRepo
import com.mayman.kmp.core.domain.Result
import com.mayman.kmp.core.domain.map

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//
internal class MoviesRepoImpl(
    private val remoteMovieDataSource: RemoteMovieDataSource
) : MoviesRepo {

    override suspend fun getMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource.getMovies().map { response ->
            response.results?.filterNotNull()?.map { it.toBook() } ?: emptyList()
        }
    }

    override suspend fun searchMovie(query: String): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource.searchMovie(query).map { response ->
            response.results?.filterNotNull()?.map { it.toBook() } ?: emptyList()
        }
    }

}