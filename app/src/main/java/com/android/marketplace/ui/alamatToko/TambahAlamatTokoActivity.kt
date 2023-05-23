package com.android.marketplace.ui.alamatToko

import android.os.Bundle
import com.android.marketplace.core.data.source.model.AlamatToko
import com.android.marketplace.core.data.source.model.Toko
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.CreateTokoRequest
import com.android.marketplace.databinding.ActivityBukaTokoBinding
import com.android.marketplace.databinding.ActivityTambahAlamatTokoBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.toko.TokoViewModel
import com.android.marketplace.util.Prefs
import com.android.marketplace.util.defaultError
import com.android.marketplace.util.getTokoId
import com.inyongtisto.myhelper.extension.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TambahAlamatTokoActivity : MyActivity() {

    private lateinit var binding: ActivityTambahAlamatTokoBinding
    private val viewModel: AlamatTokoViewModel by viewModel()
    private var provinsiId: Int? = null
    private var kotaId: Int? = null
    private var kecamatanId: Int? = null
    private var provinsi: String? = null
    private var kota: String? = null
    private var kecamatan: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahAlamatTokoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Tambah Alamat")

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        // 0, 1, 2, 3
        val listProvinsi = listOf("Pilih Provinsi", "Sulawesi Selatan", "Sulawesi Tengah", "Sulawesi Barat")
        val listKota = listOf("Pilih Kota", "Makassar", "Palu", "Mamuju")
        val listKecamatan = listOf("Pilih Kecamatan", "Mamajang", "Pantoloan", "Kalukku")

        binding.spnProvinsi.setOnPositionSelectedListener(this, listProvinsi) {
            if (it == 0){
                provinsiId = null
            } else {
                // it == 1
                provinsiId = 10
                provinsi = listProvinsi[it]
            }
        }

        binding.spnKota.setOnPositionSelectedListener(this, listKota) {
            if (it == 0){
                kotaId = null
            } else {
                // it == 1
                kotaId = 399
                kota = listKota[it]
            }
        }

        binding.spnKecamatan.setOnPositionSelectedListener(this, listKecamatan) {
            if (it == 0){
                kecamatanId = null
            } else {
                // it == 1
                kecamatanId = 5505
                kecamatan = listKecamatan[it]
            }
        }
    }

    private fun mainButton(){
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) simpan()
            }

        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtLokasi.isEmpty()) return false
            if (edtAlamat.isEmpty()) return false
            if (edtKodePos.isEmpty()) return false
            if (edtEmail.isEmpty()) return false
            if (edtPhone.isEmpty()) return false
            if (provinsiId == null) {
                toastSimple("Pilih Provinsi Anda")
                return false
            }
            if (kotaId == null) {
                toastSimple("Pilih Kota Anda")
                return false
            }
            if (kecamatanId == null) {
                toastSimple("Pilih Kecamatan Anda")
                return false
            }
        }
        return true
    }

    private fun simpan(){
        val reqData = AlamatToko(
            tokoId = getTokoId(),
            lokasi = binding.edtLokasi.getString(),
            alamat = binding.edtAlamat.getString(),
            provinsi = provinsi,
            kota = kota,
            kecamatan = kecamatan,
            provinsiId = provinsiId,
            kotaId = kotaId,
            kecamatanId = kecamatanId,
            kodepost = binding.edtKodePos.getString(),
            email = binding.edtEmail.getString(),
            phone = binding.edtPhone.getString(),
        )
        viewModel.create(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Alamat Berhasil Ditambahkan")
                    onBackPressed()
                }
                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message.defaultError())
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}