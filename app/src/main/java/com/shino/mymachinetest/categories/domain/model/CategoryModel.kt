package com.shino.mymachinetest.categories.domain.model

import com.shino.mymachinetest.products.domain.model.ProductModel

data class CategoryModel(
    val id : String,
    val products: List<ProductModel>,
    val title: String
)