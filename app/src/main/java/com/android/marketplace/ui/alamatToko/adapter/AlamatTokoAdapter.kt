package com.android.marketplace.ui.alamatToko.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.databinding.ItemAlamatTokoBinding
import com.android.marketplace.databinding.ItemHomeCategoryBinding
import com.android.marketplace.ui.alamatToko.EditAlamatTokoActivity
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.logs
import com.inyongtisto.myhelper.extension.popUpMenu
import com.inyongtisto.myhelper.extension.toJson

@SuppressLint("NotifyDataSetChanged")
class AlamatTokoAdapter(val onDelete: (Item: AlamatToko, pos: Int) -> Unit) :
    RecyclerView.Adapter<AlamatTokoAdapter.viewHolder>() {

    private var data = ArrayList<AlamatToko>()

    inner class viewHolder(private val itemBinding: ItemAlamatTokoBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: AlamatToko, position: Int){
            itemBinding.apply {
                tvKota.text = item.kota
                var kecamatan = ""
                if (item.kecamatan != null) kecamatan = ", Kec. ${item.kecamatan}"
                tvAlamat.text =
                    "${item.alamat}$kecamatan, ${item.kota}, ${item.provinsi}, ${item.kodepost}"
                tvEmail.text = item.email
                tvPhone.text = item.phone

                val context = root.context
                btnMenu.setOnClickListener {
                    val listMenu = listOf("Detail", "Hapus")
                    context.popUpMenu(btnMenu, listMenu) {
                        when (it) {
                            "Detail" -> context.intentActivity(EditAlamatTokoActivity::class.java, item.toJson())
                            "Hapus" -> onDelete.invoke(item, adapterPosition)
                        }
                    }
                }
            }
        }
    }

    fun removeAt(index: Int){
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addItems(items: List<AlamatToko>){
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(ItemAlamatTokoBinding.inflate(
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