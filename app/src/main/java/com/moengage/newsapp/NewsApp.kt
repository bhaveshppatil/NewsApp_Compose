package com.moengage.newsapp

import android.app.Application
import com.moengage.newsapp.remote.MockNetwork
import com.moengage.newsapp.remote.NetworkMonitor
import com.moengage.newsapp.remote.NewsApiService
import com.moengage.newsapp.remote.NewsRepositoryImpl
import com.moengage.newsapp.remote.SafeApiRequest
import com.moengage.newsapp.ui.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val newsModule = module {
            single { }
            single<NetworkMonitor> { MockNetwork() }
            single { NewsViewModel(get(), get()) }
            single { SafeApiRequest(get()) }
            single { NewsRepositoryImpl(get(), get()) }
            single { NewsApiService.create() }
        }

        startKoin {
            printLogger()
            androidContext(this@NewsApp)
            modules(newsModule)
        }
    }
}