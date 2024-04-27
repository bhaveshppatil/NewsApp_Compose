package com.moengage.newsapp.remote

import com.moengage.newsapp.model.NewsResponse
import com.moengage.newsapp.utils.KodeinTag.NEWS.ENDPOINT
import com.moengage.newsapp.utils.Result
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface NewsApiService {

    @GET(ENDPOINT)
    suspend fun getListOfNews(
    ): NewsResponse

    companion object {
        private const val BASE_URL = "https://candidate-test-data-moengage.s3.amazonaws.com/"

        fun create(): NewsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(createOkHttpClient()) // Use the custom OkHttpClient
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsApiService::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                // Add your desired timeouts
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                // Add the logging interceptor
                .addInterceptor(loggingInterceptor)
                // You can add more interceptors here if needed
                .build()
        }
    }
}