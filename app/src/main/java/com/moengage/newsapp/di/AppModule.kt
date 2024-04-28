package com.moengage.newsapp.di

import com.moengage.newsapp.remote.MockNetwork
import com.moengage.newsapp.remote.NetworkMonitor
import com.moengage.newsapp.remote.NewsRepositoryImpl
import com.moengage.newsapp.ui.repository.NewsRepository
import com.moengage.newsapp.ui.viewmodel.NewsViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.singleton

val appModule = DI.Module("app") {
    bindSingleton<NetworkMonitor> { MockNetwork() }
    bind<NewsRepository> { singleton { NewsRepositoryImpl(di) } }
    bindProvider { NewsViewModel(instance()) }

}