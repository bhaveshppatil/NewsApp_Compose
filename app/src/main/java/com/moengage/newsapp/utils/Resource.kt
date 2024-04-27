package com.moengage.newsapp.utils

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorCode: Int?,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, 0)
        }

        fun <T> error(msg: String, data: T?, errorCode: Int?): Resource<T> {
            return Resource(Status.ERROR, data, msg, errorCode)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, 0)
        }
    }
}