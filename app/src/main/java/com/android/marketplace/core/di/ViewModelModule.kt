package com.android.marketplace.core.di

import com.android.marketplace.core.data.repository.BaseViewModel
import com.android.marketplace.ui.alamatToko.AlamatTokoViewModel
import com.android.marketplace.ui.auth.AuthViewModel
import com.android.marketplace.ui.navigation.home.HomeViewModel
import com.android.marketplace.ui.kategori.KategoriViewModel
import com.android.marketplace.ui.navigation.NavViewModel
import com.android.marketplace.ui.produk.ProdukViewModel
import com.android.marketplace.ui.slider.SliderViewModel
import com.android.marketplace.ui.toko.TokoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{AuthViewModel(get())}
    viewModel{TokoViewModel(get())}
    viewModel{NavViewModel(get())}
    viewModel{AlamatTokoViewModel(get())}
    viewModel{ProdukViewModel(get())}
    viewModel{KategoriViewModel(get())}
    viewModel{BaseViewModel(get())}
    viewModel{ HomeViewModel(get()) }
    viewModel{SliderViewModel(get())}
}