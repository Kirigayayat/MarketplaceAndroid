package com.android.marketplace.core.data.source.remote.response

data class ProdukResponse(
    val code: Int? = null,
    val message: String? = null,
    val data: Produk? = null
) {
    data class Produk(
        val id: Int? = null,
        val name: String? = null,
        val kota: String? = null,
        val userId: Int? = null
    )
}