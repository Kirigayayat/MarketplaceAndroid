package com.android.marketplace.ui.slider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.android.marketplace.core.data.repository.CategoryRepository
import com.android.marketplace.core.data.repository.SliderRepository
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.remote.request.KategoriRequest
import com.android.marketplace.core.data.source.remote.request.SliderRequest

class SliderViewModel(private val repo: SliderRepository) :ViewModel() {

    fun get() = repo.getSlider().asLiveData()
    fun create(data: SliderRequest) = repo.createSlider(data).asLiveData()
    fun update(data: SliderRequest) = repo.updateSlider(data).asLiveData()
    fun delete(id : Int?) = repo.deleteSlider(id).asLiveData()

}