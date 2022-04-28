package com.shino.mymachinetest.core.data.dto


import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.shino.mymachinetest.core.data.source.local.CategoriesEntity
import com.shino.mymachinetest.products.domain.model.ProductModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryDto(
    @Json(name = "products")
    val productDtos: List<ProductDto>?,
    @Json(name = "title")
    val title: String?
) {
    fun toCategoriesEntity(): CategoriesEntity {
        return CategoriesEntity(
            productsEntity = productDtos?.map { it.toProductEntity() } ?: emptyList(),
            title = title ?: "Not found"
        )
    }
}