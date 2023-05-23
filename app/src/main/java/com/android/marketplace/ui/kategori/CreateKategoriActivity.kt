package com.android.marketplace.ui.kategori

import android.app.Activity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.activity.result.contract.ActivityResultContracts
import com.android.marketplace.core.data.repository.BaseViewModel
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.KategoriRequest
import com.android.marketplace.databinding.ActivityCreateKategoriBinding
import com.android.marketplace.databinding.ActivityCreateProdukBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.produk.adapter.AddImageAdapter
import com.android.marketplace.util.*
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class CreateKategoriActivity : MyActivity() {

    private lateinit var binding: ActivityCreateKategoriBinding
    private val viewModel: KategoriViewModel by viewModel()
    private val viewModelBase: BaseViewModel by viewModel()
    private var fileImage: File? = null
    private val category by extra<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = if (category == null) "Tambah Kategori" else "Detail Kategori"
        setToolbar(binding.lyToolbar.toolbar, title)

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        category?.let {
            binding.apply {
                edtName.setText(it.name)
                btnAddFoto.setImagePicasso(it.image.toUrlCategory())
            }
        }
    }

    private fun mainButton(){
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (category == null) { // create category
                    if (validate()) upload()
                } else { // update category
                    if (fileImage != null) {
                        upload()
                    } else {
                        update()
                    }
                }
            }
            btnAddFoto.setOnClickListener {
                picImage()
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (fileImage == null) {
                toastWarning("Pilih gambar kategori")
                return false
            }
        }
        return true
    }

    private fun create(imageName: String?){
        val reqData = KategoriRequest(
            name = binding.edtName.getString(),
            image = imageName
        )

        viewModel.create(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Berhasil menambah kategori")
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

    private fun update(imageName: String? = null) {
        val reqData = KategoriRequest(
            name = binding.edtName.getString(),
            image = imageName,
            id = category?.id
        )

        viewModel.update(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Berhasil mengubah kategori")
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

    private fun picImage(){
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080,1080,true)
            .provider(ImageProvider.BOTH) //Or bothCameraGallery()
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!
                fileImage = File(uri.path!!)

                Picasso.get()
                    .load(fileImage!!)
                    .into(binding.btnAddFoto)
            }
        }

    private fun upload() {
        val file = fileImage.toMultipartBody()
        viewModelBase.upload(Constants.pathCategory, file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val imageName = it.body
                    if (category == null) {
                        create(imageName)
                    } else {
                        update(imageName)
                    }
                }
                State.ERROR -> {
                    toastError(it.message ?: "Error")
                    progress.dismiss()
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