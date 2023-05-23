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

class CategoryRepository(val local: LocalDataSource, val remote: RemoteDataSource ) {

    fun getCategory() = flow {
        emit(Resource.loading(null))
        try {
            remote.getCategory().let {
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

    fun createCategory(data: KategoriRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.createCategory(data).let {
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

    fun updateCategory(data: KategoriRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.updateCategory(data).let {
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

    fun deleteCategory(id: Int?) = flow {
        emit(Resource.loading(null))
        try {
            remote.deleteCategory(id).let {
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