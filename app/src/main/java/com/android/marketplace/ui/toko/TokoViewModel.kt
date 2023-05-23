package com.android.marketplace.ui.toko

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.core.data.source.remote.request.LoginRequest
import com.android.marketplace.core.data.source.remote.request.RegisterRequest
import com.android.marketplace.core.data.source.remote.request.UpdateProfilRequest
import okhttp3.MultipartBody

class TokoViewModel(val repo: AppRepository) :ViewModel() {

    fun createToko(data: CreateTokoRequest) = repo.createToko(data).asLiveData()

}