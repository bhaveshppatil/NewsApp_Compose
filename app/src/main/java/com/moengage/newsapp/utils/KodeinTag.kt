package com.moengage.newsapp.utils

object KodeinTag {
    const val appName = "appName"

    object BaseUrl {
        const val baseUrl = "base-url"
    }

    object Category {
        const val android = "Android"
    }

    object Feed {
        const val feed_tag = "news-api-feed"
    }

    object ResponseType {
        const val static = "staticResponse.json"
    }

    object NEWS {
        const val ENDPOINT = "${Category.android}/${Feed.feed_tag}/${ResponseType.static}"
    }
}