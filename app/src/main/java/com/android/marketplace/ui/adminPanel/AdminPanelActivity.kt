package com.android.marketplace.ui.adminPanel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.marketplace.R
import com.android.marketplace.databinding.ActivityAdminPanelBinding
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.databinding.ActivityNavigationBinding
import com.android.marketplace.databinding.ActivityTokoSayaBinding
import com.android.marketplace.ui.alamatToko.ListAlamatTokoActivity
import com.android.marketplace.ui.kategori.ListKategoriAdminActivity
import com.android.marketplace.ui.produk.CreateProdukActivity
import com.android.marketplace.ui.produk.ListProdukTokoActivity
import com.android.marketplace.ui.slider.ListSliderAdminActivity
import com.android.marketplace.util.Constants
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class AdminPanelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminPanelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Admin")

        setData()
        setupListener()
    }

    private fun setData(){

    }

    private fun setupListener(){
        binding.apply {
            btnCategory.setOnClickListener {
                intentActivity(ListKategoriAdminActivity::class.java)
            }

            btnSlider.setOnClickListener {
                intentActivity(ListSliderAdminActivity::class.java)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}