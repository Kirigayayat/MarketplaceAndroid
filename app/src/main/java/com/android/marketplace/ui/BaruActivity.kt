package com.android.marketplace.ui

import android.os.Bundle
import com.android.marketplace.core.data.source.model.Toko
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.toko.TokoViewModel
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BaruActivity : MyActivity() {

    private lateinit var binding: ActivityBukaTokoBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukaTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Buat Toko")

        mainButton()
    }

    private fun mainButton(){
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}