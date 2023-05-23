package com.android.marketplace.core.data.source.remote.request

data class UpdateProfilRequest(
    val id: Int,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null
)