package com.android.marketplace.ui.kategori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.CategoryRepository
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.remote.request.KategoriRequest

class KategoriViewModel(private val repo: CategoryRepository) :ViewModel() {

    fun get() = repo.getCategory().asLiveData()
    fun create(data: KategoriRequest) = repo.createCategory(data).asLiveData()
    fun update(data: KategoriRequest) = repo.updateCategory(data).asLiveData()
    fun delete(id : Int?) = repo.deleteCategory(id).asLiveData()

}