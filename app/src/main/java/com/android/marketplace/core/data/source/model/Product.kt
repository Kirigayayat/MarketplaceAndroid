package com.android.marketplace.core.data.source.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Product(
    val id: Int? = null,
    val name: String?,
    val price: Int?,
    val tokoId: Int? = null,
    val categoryId: Int? = null,
    val description: String? = null,
    val weight: Int? = null,
    val stock: Int? = null,
    val isActive: Boolean? = null,
    val images: String? = null,
    val category: Category? = null,
    val store: Toko? = null,

    val pengiriman: String? = null,
    val sold: Int? = null,
    val rating: Double? = null,
    val discount: Int = 0,
    val grosir: Boolean? = null,
    val imageDummy: Int? = null
) : Parcelable {
    fun firstImage(): String {
        val splitImages = images?.split("|")
        val imageProduct = if (splitImages.isNullOrEmpty()) {
            images ?: ""
        } else {
            splitImages[0]
        }
        return imageProduct
    }
}