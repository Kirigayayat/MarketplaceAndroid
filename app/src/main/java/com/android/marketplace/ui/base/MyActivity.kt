package com.android.marketplace.ui.base

import com.inyongtisto.myhelper.base.CustomeActivity

abstract class MyActivity : CustomeActivity(){
    fun onBack() {
        onBackPressedDispatcher.onBackPressed()
    }
}