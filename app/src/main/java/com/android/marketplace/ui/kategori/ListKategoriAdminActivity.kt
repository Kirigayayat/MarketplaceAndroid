package com.android.marketplace.ui.kategori

import android.os.Bundle
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.ActivityAlamatTokoListBinding
import com.android.marketplace.databinding.ActivityListDataBinding
import com.android.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.kategori.adapter.KategoriAdminAdapter
import com.android.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListKategoriAdminActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: KategoriViewModel by viewModel()
    private var adapter = KategoriAdminAdapter(
        onClick = {
            intentActivity(CreateKategoriActivity::class.java, it)
        }, onDelete = { item, pos ->
            confirmDelete(item, pos)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Daftar Kategori")

        setupUI()
        mainButton()
        getData()
        setupAdapter()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun confirmDelete(item: Category, pos: Int) {
        showConfirmDialog(
            "Hapus Kategori",
            "Apakah anda yakin ingin menghapus Kategori ini?",
            "Hapus"
        ) {
            onDelete(item, pos)
        }
    }

    private fun onDelete(item: Category, pos: Int){
        viewModel.delete(item.id).observe(this){
            when (it.state) {
                State.SUCCESS -> {
                    adapter.removeAt(pos)
                    progress.dismiss()
                    toastSuccess("Kategori dihapus")
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
                intentActivity(CreateKategoriActivity::class.java)
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