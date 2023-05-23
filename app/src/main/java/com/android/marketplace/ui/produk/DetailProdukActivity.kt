package com.android.marketplace.ui.produk

import android.annotation.SuppressLint
import android.os.Bundle
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.model.Toko
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.databinding.ActivityDetailProdukBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.produk.adapter.ImageProdukSliderAdapter
import com.android.marketplace.ui.toko.TokoViewModel
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProdukActivity : MyActivity() {

    private lateinit var binding: ActivityDetailProdukBinding
    private val viewModel: TokoViewModel by viewModel()
    private val product by extra<Product>()
    private val adapter = ImageProdukSliderAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product?.let {
            logs("product:" + it.toJson())
            binding.tvNameProduct.text = it.name
        }

        mainButton()
        setAdapter()
        setupData()
    }

    @SuppressLint("SetTextI18n")
    private fun setupData() {
        binding.apply {
            product?.let {
                val harga = it.price ?: 0
                tvPrice.text = harga.toRupiah()
                tvStok.text = it.stock.formatCurrency()
                tvSold.text = it.sold.formatCurrency()
                tvCity.text = it.store?.address?.kota
                tvNameStore.text = it.store?.name
                tvDescription.text = it.description
                if (it.discount != 0) {
                    val discount = it.discount.toDouble()
                    lyDiskon.visible(discount > 0)
                    tvDiscount.text = "${it.discount}%"
                    tvPrice.text = (harga - ((discount / 100) * harga)).toRupiah()
                    tvHargaAsli.text = it.price.toRupiah()
                    tvHargaAsli.coret()
                }
            }
        }
    }

    private fun setAdapter() {
        binding.apply {
            val image = product?.images ?: ""
            val splitImages = image.split("|")
            adapter.addItems(splitImages)
            sliderImage.adapter = adapter
        }
    }

    private fun mainButton() {
        binding.apply {
            btnBack.setOnClickListener {
                onBack()
            }
        }
    }
}