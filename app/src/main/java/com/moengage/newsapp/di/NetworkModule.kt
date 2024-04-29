package com.moengage.newsapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moengage.newsapp.BuildConfig
import com.moengage.newsapp.remote.NewsApiService
import com.moengage.newsapp.remote.helpers.NetworkInterceptor
import com.moengage.newsapp.remote.helpers.NetworkMonitor
import com.moengage.newsapp.remote.helpers.SafeApiRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = DI.Module("network") {
    bindSingleton { provideLoggingInterceptor() }
    bindSingleton { provideGson() }
    bindSingleton { provideGsonConverterFactory(instance()) }
    bindSingleton { provideOkHttpClient(instance(), instance()) }
    bindSingleton { SafeApiRequest(instance()) }
    bindSingleton { provideRetrofit(instance(), instance(), BuildConfig.NEWS_BASE_URL) }
    bindSingleton { provideNewsApiService(instance()) }
    bindSingleton { provideNetworkInterceptor(instance()) }

}

fun provideNewsApiService(retrofit: Retrofit): NewsApiService =
    retrofit.create((NewsApiService::class.java))

private fun provideLoggingInterceptor() =
    HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

fun provideNetworkInterceptor(networkMonitor: NetworkMonitor) = NetworkInterceptor(networkMonitor)

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkInterceptor: NetworkInterceptor,
) = provideOkHttpClientBuilder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(networkInterceptor)
    .build()

private fun provideOkHttpClientBuilder() = OkHttpClient().newBuilder().apply {
    callTimeout(40, TimeUnit.SECONDS)
    connectTimeout(40, TimeUnit.SECONDS)
    readTimeout(40, TimeUnit.SECONDS)
    writeTimeout(40, TimeUnit.SECONDS)
}

fun provideGson(): Gson {
    return GsonBuilder().create()
}

fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
    return GsonConverterFactory.create(gson)
}

fun provideRetrofit(
    okHttpClient: OkHttpClient, converterFactory: Converter.Factory, url: String,
): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}