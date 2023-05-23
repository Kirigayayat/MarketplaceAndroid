package com.android.marketplace.ui.produk

import android.app.Activity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.activity.result.contract.ActivityResultContracts
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.ActivityCreateProdukBinding
import com.android.marketplace.ui.base.MyActivity
import com.android.marketplace.ui.kategori.SelectKategoriActivity
import com.android.marketplace.ui.produk.adapter.AddImageAdapter
import com.android.marketplace.util.*
import com.github.drjacky.imagepicker.ImagePicker
import com.github.drjacky.imagepicker.constant.ImageProvider
import com.inyongtisto.myhelper.extension.*
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class CreateProdukActivity : MyActivity() {

    private lateinit var binding: ActivityCreateProdukBinding
    private val viewModel: ProdukViewModel by viewModel()
    private var selectedCategory: Category? = null

    private val adapterImage = AddImageAdapter(
        onAddImage = {
            picImage()
        },
        onDeleteImage = {
            removeImage(it)
        }
    )

    private fun removeImage(index: Int) {
        listImages.removeAt(index)
        adapterImage.removeAt(index)

        if (!listImages.any { it.isEmpty() }) {
            listImages.add("")
            adapterImage.addItems(listImages)
            binding.btnTambahFoto.toVisible()
        }
    }

    private var listImages = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Tambah Produk")

        setupUI()
        mainButton()
        setupImageProduct()
    }

    private fun setupUI() {

    }

    private fun setupImageProduct() {
        listImages.add("0511681_1680587643632_croppedImg.jpg")
        listImages.add("0425362_1684210998370_croppedImg.jpg")
        listImages.add("0425362_1684210998370_croppedImg.jpg")
        listImages.add("asset_produk1.jpg")
        listImages.add("") // sama aja dgn empty
        adapterImage.addItems(listImages)
        binding.rvImage.adapter = adapterImage
    }

    private fun mainButton() {
        binding.apply {
            lyToolbar.btnSimpan.toVisible()
            lyToolbar.btnSimpan.setOnClickListener {
                if (validate()) create()
            }

            edtHarga.addRupiahListener()



            edtKategori.setOnClickListener {
                intentActivityResult(SelectKategoriActivity::class.java, launcherCategory)
            }
        }
    }

    private val launcherCategory =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val str: String? = it.data?.getStringExtra("extra")
                selectedCategory = str.toModel(Category::class.java)
                binding.edtKategori.setText(selectedCategory?.name)
            }
        }

    private fun validate(): Boolean {
        binding.apply {
            if (edtName.isEmpty()) return false
            if (edtHarga.isEmpty()) return false
            if (edtBerat.isEmpty()) return false
            if (edtStok.isEmpty()) return false
            if (edtDeskripsi.isEmpty()) return false
        }
        return true
    }

    private fun create() {
        var images = ""

        listImages.forEach {
            if (it.isNotEmpty()) images += "$it|"
        }

        images = images.dropLast(1)

        val reqData = Product(
            tokoId = getTokoId(),
            name = binding.edtName.getString(),
            price = binding.edtHarga.getString().remove(",").toInt(),
            description = binding.edtDeskripsi.getString(),
            weight = binding.edtBerat.getString().toInt(),
            stock = binding.edtStok.getString().toInt(),
            images = images,
            categoryId = selectedCategory?.id
        )
        viewModel.create(reqData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    toastSuccess("Berhasil menambah produk")
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

    private fun picImage() {
        ImagePicker.with(this)
            .crop()
            .maxResultSize(1080, 1080, true)
            .createIntentFromDialog { launcher.launch(it) }
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data!!

                val fileImage = File(uri.path!!)
                upload(fileImage)
            }
        }

    private fun upload(fileImage: File) {
        val file = fileImage.toMultipartBody()
        viewModel.upload(file).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val tempImages = listImages.filter { image -> image.isNotEmpty() } as ArrayList
                    tempImages.add(it.body ?: "image")
                    if (tempImages.size < 5) tempImages.add("")
                    else binding.btnTambahFoto.toGone()

                    listImages = tempImages
                    adapterImage.addItems(tempImages)
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