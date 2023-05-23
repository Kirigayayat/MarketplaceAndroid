package com.android.marketplace.core.data.source.remote

import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.network.ApiService
import com.android.marketplace.core.data.source.remote.request.*
import com.android.marketplace.util.getTokoId
import okhttp3.MultipartBody

class RemoteDataSource(val api: ApiService) {

    suspend fun login(data: LoginRequest) = api.login(data)
    suspend fun register(data: RegisterRequest) = api.register(data)
    suspend fun updateUser(data: UpdateProfilRequest) = api.updateUser(data.id, data)
    suspend fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = api.uploadUser(id, fileImage)
    suspend fun createToko(data: CreateTokoRequest) = api.createToko(data)
    suspend fun getUser(id: Int? = null) = api.getUser(id)
    suspend fun getAlamatToko() = api.getAlamatToko(getTokoId())
    suspend fun createAlamatToko(data: AlamatToko) = api.createAlamatToko(data)
    suspend fun updateAlamatToko(data: AlamatToko) = api.updateAlamatToko(data.id, data)
    suspend fun deleteAlamatToko(id: Int?) = api.deleteAlamatToko(id)
    suspend fun getProduk() = api.getProduk(getTokoId())
    suspend fun createProduk(data: Product) = api.createProduk(data)
    suspend fun updateProduk(data: Product) = api.updateProduk(data.id, data)
    suspend fun deleteProduk(id: Int?) = api.deleteProduk(id)
    suspend fun getOneProduk(id: Int?) = api.getOneProduk(id)
    suspend fun uploadProduk(fileImage: MultipartBody.Part? = null) = api.uploadProduk(fileImage)
    suspend fun getCategory() = api.getCategory()
    suspend fun createCategory(data: KategoriRequest) = api.createCategory(data)
    suspend fun updateCategory(data: KategoriRequest) = api.updateCategory(data.id, data)
    suspend fun deleteCategory(id: Int?) = api.deleteCategory(id)
    suspend fun uploadImage(path: String, fileImage: MultipartBody.Part? = null) = api.uploadImage(path, fileImage)
    suspend fun getHome() = api.getHome()
    suspend fun getSlider() = api.getSlider()
    suspend fun createSlider(data: SliderRequest) = api.createSlider(data)
    suspend fun updateSlider(data: SliderRequest) = api.updateSlider(data.id, data)
    suspend fun deleteSlider(id: Int?) = api.deleteSlider(id)

}