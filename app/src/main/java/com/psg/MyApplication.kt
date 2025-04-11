package com.psg



import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import timber.log.Timber

class MyApplication : Application() {
    class MyRepository()
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            modules(appModule)
        }
    }

    val appModule = module {
        singleOf(::MyRepository)
    }
}
