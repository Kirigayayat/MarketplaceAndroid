package com.android.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.marketplace.ui.navigation.NavigationActivity
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.RegisterRequest
import com.android.marketplace.databinding.ActivityRegisterBinding
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
    }

    fun setData(){
        binding.btnDaftar.setOnClickListener{
            register()
        }
    }

    private fun register(){

        if (binding.edtName.isEmpty()) return
        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return
        if (binding.edtPhone.isEmpty()) return

        val body = RegisterRequest(
            binding.edtName.text.toString(),
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString(),
            binding.edtPhone.text.toString()
        )

        viewModel.register(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    dismisLoading()
                    showToast("Selamat datang " + it.body?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    dismisLoading()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    showLoading()
                }
            }
        }
    }

}