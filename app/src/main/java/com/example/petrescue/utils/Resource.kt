package com.example.petrescue.utils

import java.io.IOException

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()
    class Succeded<out T>(val data: T) : Resource<T>()
    sealed class Failure(val retry: (() -> Any?)?) : Resource<Nothing>() {
        class NetworkError(retry: (() -> Any?)? = null) : Failure(retry)
        class UnknownError(retry: (() -> Any?)? = null) : Failure(retry)
    }

    companion object {
        inline fun <T> success(data: T): Resource<T> = Succeded(data)
        inline fun <T> failure(
            exception: Throwable,
            noinline retry: (() -> Any?)? = null
        ): Resource<T> {
            return when (exception) {
                is IOException -> Failure.NetworkError(retry)
                else -> Failure.UnknownError(retry)
            }
        }
    }
}