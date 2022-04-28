package com.shino.mymachinetest.core.data.dto


import com.shino.mymachinetest.categories.domain.model.CategoryModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResDto(
    @Json(name = "categories")
    val categories: List<CategoryDto>?,
    @Json(name = "msg")
    val msg: String?,
    @Json(name = "status")
    val status: Boolean?
)