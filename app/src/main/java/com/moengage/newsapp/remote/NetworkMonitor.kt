package com.moengage.newsapp.remote

import com.moengage.newsapp.utils.Constants.NO_INTERNET_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoInternetException(message: String = NO_INTERNET_ERROR) : Exception(message)
interface NetworkMonitor {
    val isOnline: Flow<Boolean>

    /**
     * Checks if the device has transport capabilities.
     *
     * This function checks whether device has internet capability and through which network it is connected
     *
     * @return true if the device has an active internet connection, false otherwise.
     */
    fun isCurrentlyConnected(): Boolean
}

class MockNetwork : NetworkMonitor {
    override val isOnline = flow {
        emit(true)
    }

    override fun isCurrentlyConnected() = true

}