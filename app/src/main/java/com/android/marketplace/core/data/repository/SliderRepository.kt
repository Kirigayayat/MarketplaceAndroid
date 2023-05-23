package com.android.marketplace.core.data.repository

import com.android.marketplace.core.data.source.local.LocalDataSource
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.remote.RemoteDataSource
import com.android.marketplace.core.data.source.remote.network.Resource
import com.android.marketplace.core.data.source.remote.request.*
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getErrorBody
import com.inyongtisto.myhelper.extension.logs
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import java.lang.Exception

class SliderRepository(val local: LocalDataSource, val remote: RemoteDataSource ) {

    fun getSlider() = flow {
        emit(Resource.loading(null))
        try {
            remote.getSlider().let {
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

    fun createSlider(data: SliderRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createSlider(data).let {
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

    fun updateSlider(data: SliderRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateSlider(data).let {
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

    fun deleteSlider(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteSlider(id).let {
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