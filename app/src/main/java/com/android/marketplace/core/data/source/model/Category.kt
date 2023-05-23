package com.android.marketplace.core.data.source.model

import android.os.Parcelable
import com.android.marketplace.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val parentId: Int? = null,
    val name: String?,
    val image: String? = null,
    val imageDummy: Int = R.drawable.asset_komputer,
) : Parcelable