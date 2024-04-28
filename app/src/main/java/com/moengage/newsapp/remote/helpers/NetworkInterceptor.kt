package com.moengage.newsapp.remote.helpers

import com.moengage.newsapp.remote.NetworkMonitor
import com.moengage.newsapp.remote.NoInternetException
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