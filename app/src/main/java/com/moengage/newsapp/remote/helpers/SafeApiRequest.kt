package com.moengage.newsapp.remote.helpers


import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class SafeApiRequest(
    private val networkMonitor: NetworkMonitor
) {
    suspend fun <T : Any> apiRequest(dataRequest: suspend () -> T): Result<T> {
        return try {
            if (networkMonitor.isCurrentlyConnected()) {
                Result.Success(dataRequest.invoke())
            } else
                throw NoInternetException()
        } catch (throwable: Throwable) {
            when (throwable) {
                is SocketTimeoutException -> {
                    Result.GenericError(throwable, "Timed out")
                }

                is IOException -> Result.GenericError(throwable)

                is HttpException -> {
                    val code = throwable.code()
                    throwable.response()?.errorBody()?.let {
                        Result.CustomError(
                            errorResponse = it,
                            errorCode = code,
                            exception = throwable,
                            errorMessage = getErrorMessage(code)
                        )
                    } ?: Result.GenericError(throwable)

                }

                is NoInternetException -> Result.GenericError(throwable)

                else -> {
                    Result.GenericError(throwable)
                }
            }
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            -1, 408, 504 -> "Timeout"
            401 -> {
                "Unauthorised"
            }

            404 -> "Not found"
            else -> "Something went wrong"
        }
    }

}
