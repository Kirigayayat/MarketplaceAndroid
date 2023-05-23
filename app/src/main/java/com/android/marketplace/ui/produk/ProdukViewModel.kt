package com.android.marketplace.ui.produk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AlamatRepository
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.repository.ProdukRepository
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Product
import okhttp3.MultipartBody

class ProdukViewModel(private val repo: ProdukRepository) :ViewModel() {

    fun get() = repo.getProduk().asLiveData()
    fun create(data: Product) = repo.createProduk(data).asLiveData()
    fun update(data: Product) = repo.updateProduk(data).asLiveData()
    fun delete(id : Int?) = repo.deleteProduk(id).asLiveData()
    fun getOneProduk(id : Int?) = repo.getOneProduk(id).asLiveData()
    fun upload(fileImage: MultipartBody.Part? = null) = repo.upload(fileImage).asLiveData()

}