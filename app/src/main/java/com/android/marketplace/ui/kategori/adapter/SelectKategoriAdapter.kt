package com.android.marketplace.ui.kategori.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.databinding.ItemAlamatTokoBinding
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.databinding.ItemKategoriAdminBinding
import com.android.marketplace.databinding.ItemProdukTokoBinding
import com.android.marketplace.databinding.ItemSelectKategoriBinding
import com.android.marketplace.ui.alamatToko.EditAlamatTokoActivity
import com.android.marketplace.util.toUrlCategory
import com.android.marketplace.util.toUrlProduct
import com.inyongtisto.myhelper.extension.*

@SuppressLint("NotifyDataSetChanged")
class SelectKategoriAdapter(
    val onClick: (item: Category) -> Unit ) :
    RecyclerView.Adapter<SelectKategoriAdapter.viewHolder>() {

    private var data = ArrayList<Category>()

    inner class viewHolder(private val itemBinding: ItemSelectKategoriBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Category, position: Int){
            itemBinding.apply {
                val context = root.context
                tvName.text = item.name
                val imageProduct = item.image
                imageView.setImagePicasso(imageProduct.toUrlCategory())

                lyMain.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

    fun removeAt(index: Int){
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItems(items: List<Category>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemSelectKategoriBinding.inflate(
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