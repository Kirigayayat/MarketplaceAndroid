package com.android.marketplace.ui.produk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.android.marketplace.core.data.source.model.Slider
import com.android.marketplace.databinding.ItemHomeSliderBinding
import com.android.marketplace.databinding.ItemImageProdukSliderBinding
import com.android.marketplace.util.toUrlProduct
import com.android.marketplace.util.toUrlSlider
import com.inyongtisto.myhelper.extension.setImagePicasso

class ImageProdukSliderAdapter : PagerAdapter() {
    private val data: ArrayList<String> = ArrayList()

    override fun getCount() = data.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    fun addItems(items: List<String>) {
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding =
            ItemImageProdukSliderBinding.inflate(LayoutInflater.from(container.context), container, false)
        val item = data[position]

        binding.apply {
            imageProduct.setImagePicasso(item.toUrlProduct())
        }

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}