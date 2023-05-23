package com.android.marketplace.ui.produk.adapter

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
import com.android.marketplace.databinding.ItemProdukTokoBinding
import com.android.marketplace.ui.alamatToko.EditAlamatTokoActivity
import com.android.marketplace.util.toUrlProduct
import com.inyongtisto.myhelper.extension.*

@SuppressLint("NotifyDataSetChanged")
class ProdukTokoAdapter(
    val onClick: (item: Product) -> Unit,
    val onDelete: (Item: Product, pos: Int) -> Unit) :
    RecyclerView.Adapter<ProdukTokoAdapter.viewHolder>() {

    private var data = ArrayList<Product>()

    inner class viewHolder(private val itemBinding: ItemProdukTokoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Product, position: Int){
            itemBinding.apply {
                val context = root.context
                tvName.text = item.name
                tvHarga.text = item.price.toRupiah()
                tvStok.text = item.stock.toString()

                val splitImages = item.images?.split("|")
                val imageProduk = if (splitImages.isNullOrEmpty()){
                    item.images ?: ""
                } else {
                    splitImages[0]
                }
                imgProduct.setImagePicasso(imageProduk.toUrlProduct())

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

    fun addItems(items: List<Product>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemProdukTokoBinding.inflate(
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