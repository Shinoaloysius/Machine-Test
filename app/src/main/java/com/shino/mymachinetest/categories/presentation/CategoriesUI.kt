package com.shino.mymachinetest.categories.presentation

import com.shino.mymachinetest.categories.domain.model.CategoryModel

sealed class CategoriesUI {
    object Loading : CategoriesUI()
    object Idle : CategoriesUI()
    data class CategorieSuccess(val categories : List<CategoryModel>) : CategoriesUI()
    data class Error(val message :String): CategoriesUI()
}