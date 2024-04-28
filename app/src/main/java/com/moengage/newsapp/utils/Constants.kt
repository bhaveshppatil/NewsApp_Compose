package com.moengage.newsapp.utils


enum class SortingOrder {
    ASCENDING,
    DESCENDING
}

object Constants {
    const val NO_INTERNET_ERROR = "Oops, your internet is down"
    const val INTERNET_NOT_FOUND_STATUS_CODE = 504
    const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
}

object ContentDescription {
    const val SplashImage = "Splash Image"
    const val NewsImage = "News Image"
}