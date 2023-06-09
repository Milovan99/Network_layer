package com.milovanjakovljevic.networklayer.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        fun <T> success(data: T): Result<T> = Success(data)
        fun error(message: Throwable): Result<Nothing> = Error(message)
        fun loading(): Result<Nothing> = Loading
    }
}
