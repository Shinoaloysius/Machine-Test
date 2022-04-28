package com.shino.mymachinetest.core.data.dto


import com.shino.mymachinetest.core.data.source.local.ProductEntity
import com.shino.mymachinetest.products.domain.model.ProductModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "description")
    val description: String?,
    @Json(name = "imageUrl")
    val imageUrl: String?,
    @Json(name = "price")
    val price: Int?,
    @Json(name = "title")
    val title: String?
) {
    fun toProductEntity(): ProductEntity {
        return ProductEntity(
            id = System.currentTimeMillis(),
            description = description ?: "",
            imageUrl = imageUrl ?: "",
            price = price ?: 0,
            title = title ?:""
        )
    }
}