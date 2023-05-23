package com.android.marketplace.core.data.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AlamatRepository
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.repository.ProdukRepository
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Product
import okhttp3.MultipartBody

class BaseViewModel(private val repo: AppRepository) :ViewModel() {

    fun upload(path: String, fileImage: MultipartBody.Part? = null) =
        repo.uploadImage(path, fileImage).asLiveData()

}