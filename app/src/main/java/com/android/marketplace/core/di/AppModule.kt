package com.android.marketplace.core.di

import com.android.marketplace.core.data.source.local.LocalDataSource
import com.android.marketplace.core.data.source.remote.RemoteDataSource
import com.android.marketplace.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
}