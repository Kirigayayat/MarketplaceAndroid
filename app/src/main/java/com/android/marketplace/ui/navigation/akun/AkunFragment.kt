package com.android.marketplace.ui.navigation.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.marketplace.ui.navigation.NavigationActivity
import com.android.marketplace.databinding.FragmentAkunBinding
import com.android.marketplace.ui.adminPanel.AdminPanelActivity
import com.android.marketplace.ui.toko.BukaTokoActivity
import com.android.marketplace.ui.toko.TokoSayaActivity
import com.android.marketplace.ui.updateProfil.UpdateProfilActivity
import com.android.marketplace.util.Constants.USER_URL
import com.android.marketplace.util.Prefs
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso

class AkunFragment : Fragment() {

    private var _binding: FragmentAkunBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainButton()
        return root
    }

    override fun onResume() {
        setUser()
        super.onResume()
    }

    private fun mainButton() {
        binding.btnLogout.setOnClickListener {
            Prefs.isLogin = false
            pushActivity(NavigationActivity::class.java)
        }

        binding.btnUpdate.setOnClickListener {
            intentActivity(UpdateProfilActivity::class.java)
        }

        binding.btnAdmin.setOnClickListener {
            intentActivity(AdminPanelActivity::class.java)
        }

    }

    private fun setUser() {
        val user = Prefs.getUser()
        if (user != null){
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                tvInisial.text = user.name.getInitial()

                Picasso.get().load(USER_URL + user.image).into(binding.imageProfile)

                if (user.toko != null) {
                    statusToko.toGone()
                    namaToko.text = user.toko?.name
                    binding.btnToko.setOnClickListener {
                        intentActivity(TokoSayaActivity::class.java)
                    }
                } else {
                    binding.btnToko.setOnClickListener {
                        intentActivity(BukaTokoActivity::class.java)
                    }
                }
                logs("user:" + Prefs.getUser())
                btnAdmin.visible(Prefs.getUser()?.isAdmin() ?: false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}