package com.android.marketplace.ui.navigation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.util.toUrlCategory
import com.inyongtisto.myhelper.extension.setImagePicasso

@SuppressLint("NotifyDataSetChanged")
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.viewHolder>() {

    private var data = ArrayList<Category>()

    inner class viewHolder(private val itemBinding: ItemHomeCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Category, position: Int){
            itemBinding.apply {
                tvName.text = item.name
                imageView.setImagePicasso(item.image?.toUrlCategory())
            }
        }
    }

    fun addItems(items: List<Category>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHomeCategoryBinding.inflate(
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