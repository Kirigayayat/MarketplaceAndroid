package com.android.marketplace.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.AppRepository
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest

class NavViewModel(val repo: AppRepository) :ViewModel() {

    fun getUser(id: Int) = repo.getUser(id).asLiveData()

}