package com.android.marketplace.core.di

import com.android.marketplace.core.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
    single { AlamatRepository(get(), get()) }
    single { ProdukRepository(get(), get()) }
    single { CategoryRepository(get(), get()) }
    single { SliderRepository(get(), get()) }
}