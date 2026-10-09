package com.mayman.kmp.movie.domain


//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//
sealed interface DataError : com.mayman.kmp.core.domain.Error {
    enum class Remote: DataError{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUEST,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }


    enum class Local: DataError{
        DISK_FULL,
        UNKNOWN
    }
}