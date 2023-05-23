package com.android.marketplace.ui.slider.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.model.Slider
import com.android.marketplace.databinding.ItemAlamatTokoBinding
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.databinding.ItemKategoriAdminBinding
import com.android.marketplace.databinding.ItemProdukTokoBinding
import com.android.marketplace.databinding.ItemSliderAdminBinding
import com.android.marketplace.ui.alamatToko.EditAlamatTokoActivity
import com.android.marketplace.util.toUrlCategory
import com.android.marketplace.util.toUrlProduct
import com.android.marketplace.util.toUrlSlider
import com.inyongtisto.myhelper.extension.*

@SuppressLint("NotifyDataSetChanged")
class SliderAdminAdapter(
    val onClick: (item: Slider) -> Unit,
    val onDelete: (Item: Slider, pos: Int) -> Unit) :
    RecyclerView.Adapter<SliderAdminAdapter.viewHolder>() {

    private var data = ArrayList<Slider>()

    inner class viewHolder(private val itemBinding: ItemSliderAdminBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Slider, position: Int){
            itemBinding.apply {
                val context = root.context
                tvName.text = item.name

                val imageName = item.image
                imageView.setImagePicasso(imageName.toUrlSlider())

                btnEdit.setOnClickListener {
                    onClick.invoke(item)
                }

                lyMain.setOnClickListener {
                    onClick.invoke(item)
                }

                btnDelete.setOnClickListener {
                    onDelete.invoke(item, adapterPosition)
                }
            }
        }
    }

    fun removeAt(index: Int){
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItems(items: List<Slider>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemSliderAdminBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(data[position], position)
    }
}