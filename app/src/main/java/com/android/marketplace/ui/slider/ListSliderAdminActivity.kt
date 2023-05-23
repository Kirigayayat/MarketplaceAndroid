package com.android.marketplace.ui.slider

import android.os.Bundle
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Slider
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.ActivityAlamatTokoListBinding
import com.android.marketplace.databinding.ActivityListDataBinding
import com.android.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.kategori.adapter.KategoriAdminAdapter
import com.android.marketplace.ui.slider.adapter.SliderAdminAdapter
import com.android.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListSliderAdminActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: SliderViewModel by viewModel()
    private var adapter = SliderAdminAdapter(
        onClick = {
            intentActivity(CreateSliderActivity::class.java, it)
        }, onDelete = { item, pos ->
            confirmDelete(item, pos)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Daftar Slider")

        setupUI()
        mainButton()
        getData()
        setupAdapter()
    }

    override fun onResume() {
        getData()
        super.onResume()
    }

    private fun confirmDelete(item: Slider, pos: Int) {
        showConfirmDialog(
            "Hapus Slider",
            "Apakah anda yakin ingin menghapus Slider ini?",
            "Hapus"
        ) {
            onDelete(item, pos)
        }
    }

    private fun onDelete(item: Slider, pos: Int){
        viewModel.delete(item.id).observe(this){
            when (it.state) {
                State.SUCCESS -> {
                    adapter.removeAt(pos)
                    progress.dismiss()
                    toastSuccess("Slider dihapus")
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
                intentActivity(CreateSliderActivity::class.java)
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
                    binding.swipeRefresh.dismissLoading()
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
        binding.apply {
            swipeRefresh.setDefaultColor()
            swipeRefresh.setOnRefreshListener {
                getData()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}