package com.moengage.newsapp.remote

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.utils.KodeinTag.NEWS.ENDPOINT
import retrofit2.http.GET

interface NewsApiService {

    @GET(ENDPOINT)
    suspend fun getListOfNews(
    ): NewsResponse

}