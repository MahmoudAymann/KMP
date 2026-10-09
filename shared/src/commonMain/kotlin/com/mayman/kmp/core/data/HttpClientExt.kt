package com.mayman.kmp.core.data

import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

//
// Created by Mahmoud Ayman Mostafa on 08/10/2026.
//

suspend inline fun <reified T> safeApiCall(execute: () -> HttpResponse): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (e: SocketTimeoutException) {//no response from server
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) { //when server actually live but I cant reach out
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        // currentCoroutineContext().ensureActive() checks if the current coroutine context/Job has been cancelled.
        // If it was canceled, ensureActive() re-throws the CancellationException
        // so that the coroutine structured concurrency and cancellation flow are not broken or swallowed as a Result.Error(DataError.Remote.UNKNOWN)
        currentCoroutineContext().ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult<T>(response)
}


suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }

        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUEST)
        in 500..599 -> Result.Error(DataError.Remote.SERVER_ERROR)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}