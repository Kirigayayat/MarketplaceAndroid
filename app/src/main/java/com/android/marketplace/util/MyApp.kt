package com.android.marketplace.util

import android.app.Application
import com.android.marketplace.core.di.appModule
import com.android.marketplace.core.di.repositoryModule
import com.android.marketplace.core.di.viewModelModule
import com.chibatching.kotpref.Kotpref
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        startKoin(){
            androidContext(this@MyApp)
            modules(listOf(appModule, viewModelModule, repositoryModule))
        }
    }
}