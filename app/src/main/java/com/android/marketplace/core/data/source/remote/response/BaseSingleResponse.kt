package com.android.marketplace.core.data.source.remote.response

data class BaseSingleResponse<T> (
    val code :Int? = null,
    val message :String? = null,
    val data :T? = null
)