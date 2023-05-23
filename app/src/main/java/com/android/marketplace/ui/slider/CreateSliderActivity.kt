package com.android.marketplace.ui.slider

import android.app.Activity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.activity.result.contract.ActivityResultContracts
import com.android.marketplace.core.data.repository.BaseViewModel
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.model.Slider
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.core.data.source.remote.request.KategoriRequest
import com.android.marketplace.core.data.source.remote.request.SliderRequest
import com.android.marketplace.databinding.ActivityCreateKategoriBinding
import com.android.marketplace.databinding.ActivityCreateProdukBinding
import com.android.marketplace.databinding.ActivityCreateSliderBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.produk.adapter.AddImageAdapter
import com.android.marketplace.util.*
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class CreateSliderActivity : MyActivity() {

    private lateinit var binding: ActivityCreateSliderBinding
    private val viewModel: SliderViewModel by viewModel()
    private val viewModelBase: BaseViewModel by viewModel()
    private var fileImage: File? = null
    private val slider by extra<Slider>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = if (slider == null) "Tambah Slider" else "Detail Slider"
        setToolbar(binding.lyToolbar.toolbar, title)

        setupUI()
        mainButton()
    }

    private fun setupUI() {
        slider?.let {
            binding.apply {
                edtName.setText(it.name)
                imageSlider.setImagePicasso(it.image.toUrlSlider())
            }
        }
    }

    private fun mainButton(){
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (slider == null) { // create
                    if (validate()) upload()
                } else { // update
                    if (fileImage != null) {
                        upload()
                    } else {
                        update()
                    }
                }
            }
            btnAdd.setOnClickListener {
                picImage()
            }
        }
    }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (fileImage == null) {
                toastWarning("Pilih gambar slider")
                return false
            }
        }
        return true
    }

    private fun create(imageName: String?){
        val reqData = SliderRequest(
            name = binding.edtName.getString(),
            image = imageName
        )

        viewModel.create(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Berhasil menambah slider")
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
        val reqData = SliderRequest(
            name = binding.edtName.getString(),
            image = imageName,
            id = slider?.id
        )

        viewModel.update(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Berhasil mengubah slider")
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
                    .into(binding.imageSlider)
            }
        }

    private fun upload() {
        val file = fileImage.toMultipartBody()
        viewModelBase.upload(Constants.pathSlider, file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val imageName = it.body
                    if (slider == null) {
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