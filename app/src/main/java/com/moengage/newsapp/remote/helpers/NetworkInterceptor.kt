package com.moengage.newsapp.remote.helpers

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val networkMonitor: NetworkMonitor,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!networkMonitor.isCurrentlyConnected()) {
            throw NoInternetException()
        } else {
            chain.proceed(chain.request())
        }
    }
}