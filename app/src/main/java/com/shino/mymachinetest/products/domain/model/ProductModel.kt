package com.shino.mymachinetest.products.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class  ProductModel (
    val id: Long,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val title: String
        ): Parcelable

