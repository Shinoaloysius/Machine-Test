package com.shino.mymachinetest.core.data.source.local

import com.shino.mymachinetest.products.domain.model.ProductModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductEntity(
    val id : Long ,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val title: String
){
    fun toProduct():ProductModel{
        return ProductModel(
            id,description, imageUrl, price, title
        )
    }
}
