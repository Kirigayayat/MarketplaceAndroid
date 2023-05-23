package com.android.marketplace.ui.kategori

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.ActivityAlamatTokoListBinding
import com.android.marketplace.databinding.ActivityListDataBinding
import com.android.marketplace.ui.alamatToko.adapter.AlamatTokoAdapter
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.kategori.adapter.KategoriAdminAdapter
import com.android.marketplace.ui.kategori.adapter.SelectKategoriAdapter
import com.android.marketplace.util.defaultError
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectKategoriActivity : MyActivity() {

    private lateinit var binding: ActivityListDataBinding
    private val viewModel: KategoriViewModel by viewModel()
    private var adapter = SelectKategoriAdapter(
        onClick = {
            val intent = Intent()
            intent.putExtra("extra", it.toJson())
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
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

    private fun setupUI(){
        binding.apply {

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