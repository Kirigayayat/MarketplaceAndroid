package com.android.marketplace.util

fun String?.defaultError(): String {
    return this ?: Constants.DEFAULT_ERROR
}

fun String?.toUrlProduct() = Constants.BASE_URL + "storage/produk/" + this
fun String?.toUrlCategory() = Constants.BASE_URL + "storage/category/" + this
fun String?.toUrlSlider() = Constants.BASE_URL + "storage/slider/" + this