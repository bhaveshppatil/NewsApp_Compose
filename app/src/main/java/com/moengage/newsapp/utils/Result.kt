package com.moengage.newsapp.utils

const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    sealed interface Error {
        val exception: Throwable?
        val errorMessage: String
    }

    data class GenericError(
        override val exception: Throwable? = null,
        override val errorMessage: String = exception?.message ?: DEFAULT_ERROR_MESSAGE,
        val errorCode: Int? = null
    ) : Result<Nothing>(), Error

    data class CustomError<E>(
        val errorResponse: E,
        val errorCode: Int,
        override val exception: Throwable? = null,
        override val errorMessage: String = exception?.message ?: DEFAULT_ERROR_MESSAGE,
    ) : Result<Nothing>(), Error


    data object Loading : Result<Nothing>()
}