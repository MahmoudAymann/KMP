package com.mayman.kmp.di

import com.mayman.kmp.core.data.HttpClientFactory
import com.mayman.kmp.movie.data.network.KtorRemoteMovieDataSource
import com.mayman.kmp.movie.data.network.RemoteMovieDataSource
import com.mayman.kmp.movie.data.repo.MoviesRepoImpl
import com.mayman.kmp.movie.domain.repo.MoviesRepo
import com.mayman.kmp.movie.presentation.movies_list.MovieListViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

//
// Created by Mahmoud Ayman Mostafa on 09/10/2026.
//
expect val platformModule: Module

val sharedModule = module {

    single<HttpClient> { HttpClientFactory.create(get()) }
    single<RemoteMovieDataSource> {
        KtorRemoteMovieDataSource(get())
    }
    singleOf(::MoviesRepoImpl).bind<MoviesRepo>()

    viewModelOf(::MovieListViewModel)
}