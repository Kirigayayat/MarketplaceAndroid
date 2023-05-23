package com.android.marketplace.core.data.source.local

import com.android.marketplace.R
import com.android.marketplace.core.data.source.model.Category
import com.android.marketplace.core.data.source.model.Product
import com.android.marketplace.core.data.source.model.Slider

object DummyData {
    val listCategory = listOf(
        Category(id = 1, name = "Elektronik", imageDummy = R.drawable.asset_elektronik),
        Category(id = 2, name = "Handphone", imageDummy = R.drawable.asset_handphone),
        Category(id = 3, name = "Keuangan", imageDummy = R.drawable.asset_keuangan),
        Category(id = 4, name = "Komputer", imageDummy = R.drawable.asset_komputer),
        Category(id = 5, name = "Perawatan Hewan", imageDummy = R.drawable.asset_perawatan_hewan),
        Category(id = 6, name = "Topup", imageDummy = R.drawable.asset_topup),
        Category(id = 7, name = "Rumah Tangga", imageDummy = R.drawable.asset_rumah_tangga),
        Category(id = 8, name = "Travel", imageDummy = R.drawable.asset_travel),
        Category(id = 9, name = "Komputer", imageDummy = R.drawable.asset_komputer),
        Category(id = 10, name = "Semua", imageDummy = R.drawable.asset_semua),
    )

    val listSlider = listOf(
        Slider(id = 1, name = "Slider1", imageDummy = R.drawable.asset_slider1),
        Slider(id = 2, name = "Slider2", imageDummy = R.drawable.asset_slider2),
    )

   // val listProduct = listOf(
   //     Product(id = 1,
   //         name = "KF94 Mouth Mask",
   //         price = 7900,
   //         pengiriman = "Tangerang",
   //         sold = 150,
   //         rating = 5.0,
   //         discount = 0,
   //         grosir = true,
   //         imageDummy = R.drawable.asset_produk1
   //     ),
   //     Product(id = 2,
   //         name = "SHAMPOO MOBIL SALJU CAR WASH CUCI MOBIL CUCI SALJU SABUN CUCI MOBIL 5L",
   //         price = 77500,
   //         pengiriman = "Tangerang",
   //         sold = 2000,
   //         rating = 4.9,
   //         discount = 10,
   //         grosir = false,
   //         imageDummy = R.drawable.asset_produk2
   //     ),
   //     Product(id = 3,
   //         name = "Uniland Kasur Lipat 90x200 Bonus Tas Kasur - Busa Gulung Lantai Travel",
   //         price = 290000,
   //         pengiriman = "Bogor",
   //         sold = 3000,
   //         rating = 4.9,
   //         discount = 15,
   //         grosir = true,
   //         imageDummy = R.drawable.asset_produk3
   //     ),
   //     Product(id = 4,
   //         name = "Kurma Tunisia Madu Timur Tengah Premium Deglet Noor Honey",
   //         price = 43000,
   //         pengiriman = "Jakarta Barat",
   //         sold = 10000,
   //         rating = 4.9,
   //         discount = 0,
   //         grosir = true,
   //         imageDummy = R.drawable.asset_produk4
   //     ),
   //     Product(id = 5,
   //         name = "OLI MOTOR SHELL ADVANCE AX5 MATIC 20W-40 0,8L",
   //         price = 30000,
   //         pengiriman = "Jakarta Selatan",
   //         sold = 3000,
   //         rating = 4.9,
   //         discount = 20,
   //         grosir = false,
   //         imageDummy = R.drawable.asset_produk5
   //     ),
   //     Product(id = 6,
   //         name = "Loxus Cleaner 2pcs Pembersih Kerak Kamar Mandi Stainless Shower Kran",
   //         price = 141500,
   //         pengiriman = "Bekasi",
   //         sold = 10000,
   //         rating = 4.9,
   //         discount = 10,
   //         grosir = true,
   //         imageDummy = R.drawable.asset_produk6
   //     ),
   //     Product(id = 7,
   //         name = "Angola Spin Mop D15 Alat Pel Lantai Super Mop Alat Pembersih - Gray",
   //         price = 100000,
   //         pengiriman = "Tangerang",
   //         sold = 9000,
   //         rating = 4.8,
   //         discount = 0,
   //         grosir = true,
   //         imageDummy = R.drawable.asset_produk7
   //     ),
   //     Product(id = 8,
   //         name = "Tumblr Tee / T-Shirt / Kaos Pria Lengan Pendek NY Warna Hitam",
   //         price = 43000,
   //         pengiriman = "Jakarta Barat",
   //         sold = 200,
   //         rating = 4.6,
   //         discount = 15,
   //         grosir = false,
   //         imageDummy = R.drawable.asset_produk8
   //     ),
   // )
}