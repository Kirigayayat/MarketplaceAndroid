package com.android.marketplace.ui.navigation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.remote.network.State
import com.android.marketplace.databinding.FragmentHomeBinding
import com.android.marketplace.ui.base.MyFragment
import com.android.marketplace.ui.navigation.home.adapter.CategoryAdapter
import com.android.marketplace.ui.navigation.home.adapter.ProdukTerbaruAdapter
import com.android.marketplace.ui.navigation.home.adapter.ProdukTerlarisAdapter
import com.android.marketplace.ui.navigation.home.adapter.SliderAdapter
import com.android.marketplace.ui.produk.DetailProdukActivity
import com.android.marketplace.ui.produk.ProdukViewModel
import com.inyongtisto.myhelper.extension.intentActivity
import com.inyongtisto.myhelper.extension.setDefaultColor
import com.inyongtisto.myhelper.extension.toGone
import com.inyongtisto.myhelper.extension.toastError
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : MyFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapterCategory = CategoryAdapter()
    private val adapterSlider = SliderAdapter()
    private val adapterProdukTerlaris = ProdukTerlarisAdapter{
        detailProduct(it)
    }
    private val adapterProdukTerbaru = ProdukTerbaruAdapter{
        detailProduct(it)
    }
    private val viewModel: HomeViewModel by viewModel()
    private val viewModelProduct: ProdukViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupSlider()
        setupAdapter()
        setData()
        mainButton()
        getHome()
        return root
    }

    private fun setupAdapter(){
        binding.rvCategory.adapter = adapterCategory
        binding.rvProductTerlaris.adapter = adapterProdukTerlaris
        binding.rvProductTerbaru.adapter = adapterProdukTerbaru
    }

    fun setData(){

    }

    private fun setupSlider() {
        binding.apply {
            slider.adapter = adapterSlider
            slider.setPadding(40, 0, 40, 0)
        }
    }

    private fun getHome() {
        viewModel.getHome().observe(requireActivity()) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.apply {
                        pdCategory.toGone()
                        pdSlider.toGone()
                        pdProductTerbaru.toGone()
                        pdProductTerlaris.toGone()
                        swipeRefresh.isRefreshing = false
                    }

                    val categories = it.body?.categories ?: listOf()
                    val products = it.body?.products ?: listOf()
                    val sliders = it.body?.sliders ?: listOf()

                    adapterCategory.addItems(categories)
                    adapterProdukTerlaris.addItems(products)
                    adapterProdukTerbaru.addItems(products)
                    adapterSlider.addItems(sliders)
                }
                State.ERROR -> {
                }
                State.LOADING -> {

                }
            }
        }
    }

    private fun detailProduct(product: Product) {
        viewModelProduct.getOneProduk(product.id).observe(requireActivity()) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    intentActivity(DetailProdukActivity::class.java, it.body)
                }
                State.ERROR -> {
                    toastError(it.message)
                    progress.dismiss()
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    fun mainButton(){
        binding.apply {
            swipeRefresh.setDefaultColor()
            swipeRefresh.setOnRefreshListener {
                getHome()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}