package com.android.marketplace.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.databinding.ActivitySplashScreenBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.navigation.NavigationActivity
import com.android.marketplace.ui.toko.TokoViewModel
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : MyActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val viewModel: TokoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, NavigationActivity::class.java))
            finish()
        },3000)
    }
}