package com.android.marketplace.core.data.source.remote.response

import com.android.marketplace.core.data.source.model.User

data class BaseListResponse<T> (
    val code :Int? = null,
    val message :String? = null,
    val data : List<T>? = emptyList()
)