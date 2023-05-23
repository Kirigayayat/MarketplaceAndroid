package com.android.marketplace.core.data.repository

import com.android.marketplace.core.data.source.local.LocalDataSource
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.RemoteDataSource
import com.android.marketplace.core.data.source.remote.network.Resource
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.core.data.source.remote.request.LoginRequest
import com.android.marketplace.core.data.source.remote.request.RegisterRequest
import com.android.marketplace.core.data.source.remote.request.UpdateProfilRequest
import com.android.marketplace.util.Prefs
import com.android.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import java.lang.Exception

class ProdukRepository(val local: LocalDataSource, val remote: RemoteDataSource ) {

    fun getProduk() = flow {
        emit(Resource.loading(null))
        try {
            remote.getProduk().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun createProduk(data: Product) = flow {
        emit(Resource.loading(null))
        try {
            remote.createProduk(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun updateProduk(data: Product) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateProduk(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun deleteProduk(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteProduk(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun getOneProduk(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.getOneProduk(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun upload(fileImage: MultipartBody.Part? = null) = flow {
        emit(Resource.loading(null))
        try {
            remote.uploadProduk(fileImage).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val fileName = body?.data
                    emit(Resource.success(fileName))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message.defaultError(), null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message.defaultError(), null))
        }
    }
}