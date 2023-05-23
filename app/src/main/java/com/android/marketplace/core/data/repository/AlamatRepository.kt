package com.android.marketplace.core.data.repository

import com.android.marketplace.core.data.source.local.LocalDataSource
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.remote.RemoteDataSource
import com.android.marketplace.core.data.source.remote.network.Resource
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.core.data.source.remote.request.LoginRequest
import com.android.marketplace.core.data.source.remote.request.RegisterRequest
import com.android.marketplace.core.data.source.remote.request.UpdateProfilRequest
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import java.lang.Exception

class AlamatRepository(val local: LocalDataSource, val remote: RemoteDataSource ) {

    fun getAlamatToko() = flow {
        emit(Resource.loading(null))
        try {
            remote.getAlamatToko().let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?:"Error", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun createAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.createAlamatToko(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?:"Error", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun updateAlamatToko(data: AlamatToko) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateAlamatToko(data).let {
                Prefs.isLogin = true
                if (it.isSuccessful) {
                    val body = it.body()?.data
                    emit(Resource.success(body))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?:"Error", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }

    fun deleteAlamatToko(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteAlamatToko(id).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val data = body?.data
                    emit(Resource.success(data))
                } else {
                    emit(Resource.error(it.getErrorBody()?.message?:"Error", null))
                }
            }
        } catch (e: Exception) {
            emit(Resource.error(e.message?: "Terjadi Kesalahan", null))
        }
    }
}