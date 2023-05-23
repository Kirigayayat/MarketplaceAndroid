package com.android.marketplace.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.source.remote.request.LoginRequest
import com.android.marketplace.core.data.source.remote.request.RegisterRequest
import com.android.marketplace.core.data.source.remote.request.UpdateProfilRequest
import okhttp3.MultipartBody

class AuthViewModel(val repo: AppRepository) :ViewModel() {

    fun login(data: LoginRequest) = repo.login(data).asLiveData()

    fun register(data: RegisterRequest) = repo.register(data).asLiveData()

    fun updateUser(data: UpdateProfilRequest) = repo.updateUser(data).asLiveData()

    fun uploadUser(id: Int? = null, fileImage: MultipartBody.Part? = null) = repo.uploadUser(id, fileImage).asLiveData()

}