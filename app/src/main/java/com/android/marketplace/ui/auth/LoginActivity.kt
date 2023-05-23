package com.android.marketplace.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.marketplace.ui.navigation.NavigationActivity
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.LoginRequest
import com.android.marketplace.databinding.ActivityLoginBinding
import com.android.marketplace.ui.base.MyActivity
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : MyActivity() {

    private val viewModel : AuthViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        mainButton()
    }

    private fun mainButton(){
        binding.btnMasuk.setOnClickListener{
            login()
        }
        binding.tvDaftar.setOnClickListener{
            intentActivity(RegisterActivity::class.java)
        }
    }

    fun setData(){

    }

    private fun login(){

        if (binding.edtEmail.isEmpty()) return
        if (binding.edtPassword.isEmpty()) return

        val body = LoginRequest(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )

        viewModel.login(body).observe(this) {

            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    showToast("Selamat datang " + it.body?.name)
                    pushActivity(NavigationActivity::class.java)
                }
                State.ERROR -> {
                    progress.dismiss()
                    toastError(it.message ?: "Error")
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

}