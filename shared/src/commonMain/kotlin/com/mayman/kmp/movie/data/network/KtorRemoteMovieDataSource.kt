package com.mayman.kmp.movie.data.network

import com.mayman.kmp.movie.data.dto.MoviesResponseDto
import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.core.data.ApiService
import com.mayman.kmp.core.data.ApiService.BASE_URL
import com.mayman.kmp.core.data.safeApiCall
import com.mayman.kmp.core.domain.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//

class KtorRemoteMovieDataSource(
    private val httpClient: HttpClient
) : RemoteMovieDataSource {

    override suspend fun getMovies(): Result<MoviesResponseDto, DataError.Remote> {
        return safeApiCall {
            httpClient.get(urlString = BASE_URL.plus(ApiService.MOVIES_LIST)) {
                parameter("api_key", ApiService.API_KEY)
            }
        }
    }

    override suspend fun searchMovie(query: String): Result<MoviesResponseDto, DataError.Remote> {
        return safeApiCall {
            httpClient.get(urlString = BASE_URL.plus(ApiService.SEARCH_MOVIE)) {
                parameter("api_key", ApiService.API_KEY)
                parameter("query", query)
            }
        }
    }
}