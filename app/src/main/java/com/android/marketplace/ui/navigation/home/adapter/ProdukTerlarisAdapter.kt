package com.android.marketplace.ui.navigation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.R
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.databinding.ItemHomeProdukTerlarisBinding
import com.android.marketplace.ui.produk.DetailProdukActivity
import com.android.marketplace.util.toUrlProduct
import com.inyongtisto.myhelper.extension.*

@SuppressLint("NotifyDataSetChanged")
class ProdukTerlarisAdapter(
    var onClick: (Product) -> Unit,
) : RecyclerView.Adapter<ProdukTerlarisAdapter.viewHolder>() {

    private var data = ArrayList<Product>()

    inner class viewHolder(private val itemBinding: ItemHomeProdukTerlarisBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Product, position: Int){
            itemBinding.apply {
                val harga = item.price?: 0
                tvName.text = item.name
                imageView.setImagePicasso(item.firstImage().toUrlProduct())
                tvHarga.text = item.price.toRupiah()
                tvPengiriman.text = item.pengiriman ?: "Makassar"
                tvRating.text = "" + (item.rating.def(5.0)) + " | terjual " + item.sold
                tvPengiriman.text = item.store?.address?.kota

                if (item.discount != 0){
                    lyGrosir.toGone()
                    val discount = item.discount.toDouble()
                    lyDiskon.visible(discount > 0)
                    tvDiskon.text = "${item.discount}%"
                    tvHarga.text = (harga - ((discount / 100) * harga)).toRupiah()
                    tvHargaAsli.text = item.price.toRupiah()
                    tvHargaAsli.coret()
                }
                lyMain.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }

    fun addItems(items: List<Product>){
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemHomeProdukTerlarisBinding.inflate(
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