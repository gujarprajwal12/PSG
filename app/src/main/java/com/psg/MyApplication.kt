package com.psg



import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }
    }
}
