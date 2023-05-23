package com.android.marketplace.ui.toko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.marketplace.R
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.databinding.ActivityNavigationBinding
import com.android.marketplace.databinding.ActivityTokoSayaBinding
import com.android.marketplace.ui.alamatToko.ListAlamatTokoActivity
import com.android.marketplace.ui.produk.CreateProdukActivity
import com.android.marketplace.ui.produk.ListProdukTokoActivity
import com.android.marketplace.util.Constants
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.getInitial
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setToolbar
import com.inyongtisto.myhelper.extension.toGone
import com.squareup.picasso.Picasso

class TokoSayaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTokoSayaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTokoSayaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Toko Saya")

        setData()
        setupListener()
    }

    private fun setData(){
        val user = Prefs.getUser()
        if (user != null){
            binding.apply {
                if (user.toko != null) {
                    tvName.text = user.toko?.name
                    tvInisial.text = user.toko?.name.getInitial()
                    Picasso.get().load(Constants.USER_URL + user.toko?.name).into(binding.imageProfile)
                }
            }
        }
    }

    private fun setupListener(){
        binding.apply {
            btnAlamat.setOnClickListener {
                intentActivity(ListAlamatTokoActivity::class.java)
            }
            btnListProduk.setOnClickListener {
                intentActivity(ListProdukTokoActivity::class.java)
            }
            btnCreateProduk.setOnClickListener {
                intentActivity(CreateProdukActivity::class.java)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}