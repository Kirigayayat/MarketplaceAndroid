package com.android.marketplace.ui.produk

import android.os.Bundle
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.ActivityAlamatTokoListBinding
import com.android.marketplace.databinding.ActivityListDataBinding
import com.android.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.produk.adapter.ProdukTokoAdapter
import com.android.marketplace.ui.toko.TokoViewModel
import com.android.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListProdukTokoActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: ProdukViewModel by viewModel()
    private var adapter = ProdukTokoAdapter(
        onClick = {
            intentActivity(UpdateProdukActivity::class.java, it)
        }, onDelete = { item, pos ->
            confirmDeleteProduct(item, pos)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Daftar Produk")

        setupUI()
        mainButton()
        getData()
        setupAdapter()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun confirmDeleteProduct(item: Product, pos: Int) {
        showConfirmDialog(
            "Hapus Produk",
            "Apakah anda yakin ingin menghapus Produk ini?",
            "Hapus"
        ) {
            onDelete(item, pos)
        }
    }

    private fun onDelete(item: Product, pos: Int){
        viewModel.delete(item.id).observe(this){
            when (it.state) {
                State.SUCCESS -> {
                    adapter.removeAt(pos)
                    progress.dismiss()
                    toastSuccess("Produk dihapus")
                }
                State.ERROR -> {
                    showErrorDialog(it.message.defaultError())
                    progress.dismiss()
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    private fun setupUI(){
        binding.apply {
            lyToolbar.btnTambah.toVisible()
            lyToolbar.btnTambah.setOnClickListener {
                intentActivity(CreateProdukActivity::class.java)
            }
        }
    }

    private fun setupAdapter(){
        binding.rvData.adapter = adapter

    }

    private fun getData(){
        viewModel.get().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.tvError.toGone()
                    val data = it.body ?: emptyList()
                    adapter.addItems(data)

                    if (data.isEmpty()){
                        binding.tvError.toVisible()
                    }
                }
                State.ERROR -> {
                    binding.tvError.toVisible()
                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun mainButton(){

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}