package com.android.marketplace.ui.produk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.R
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.databinding.ItemGambarBinding
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.util.toUrlProduct
import com.inyongtisto.myhelper.extension.setImagePicasso
import com.inyongtisto.myhelper.extension.toVisible
import com.inyongtisto.myhelper.extension.visible

@SuppressLint("NotifyDataSetChanged")
class AddImageAdapter(var onAddImage: () -> Unit, var onDeleteImage: (Int) -> Unit) : RecyclerView.Adapter<AddImageAdapter.viewHolder>() {

    var data = ArrayList<String>()

    inner class viewHolder(private val itemBinding: ItemGambarBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: String, position: Int){
            itemBinding.apply {
                if (item.isNotEmpty()) {
                    btnAddFoto.setImagePicasso(item.toUrlProduct())
                } else btnAddFoto.setImageResource(R.drawable.asset_addphotos)

                btnClose.visible(item.isNotEmpty())
                btnAddFoto.setOnClickListener {
                    onAddImage.invoke()
                }
                btnClose.setOnClickListener {
                    onDeleteImage.invoke(adapterPosition)
                }
            }
        }
    }

    fun addItems(items: List<String>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun removeAt(index: Int){
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun allNotEmpty(){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemGambarBinding.inflate(
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