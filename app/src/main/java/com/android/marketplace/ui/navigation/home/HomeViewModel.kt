package com.android.marketplace.ui.navigation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.repository.ProdukRepository
import com.android.marketplace.core.data.source.local.DummyData
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.model.Slider
import java.util.Locale

class HomeViewModel(private val repo: AppRepository) : ViewModel() {

    fun getHome() = repo.getHome().asLiveData()
}