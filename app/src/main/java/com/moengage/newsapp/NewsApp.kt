package com.moengage.newsapp

import android.app.Application
import com.moengage.newsapp.di.appModule
import com.moengage.newsapp.di.networkModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp : Application(), DIAware {

    override val di by DI.lazy {
        import(androidXModule(this@NewsApp))
        import(appModule)
        import(networkModule)
    }

    override fun onCreate() {
        super.onCreate()
        /*      val newsModule = module {
                  single { }
                  single<NetworkMonitor> { MockNetwork() }
                  single { NewsViewModel(get(), get()) }
                  single { SafeApiRequest(get()) }
                  single { NewsRepositoryImpl(get(), get()) }
                  single { NewsApiService.create() }
              }*/

        startKoin {
            printLogger()
            androidLogger()
            androidContext(this@NewsApp)

        }
    }
}