package com.shino.mymachinetest.products.pesentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.shino.mymachinetest.products.domain.model.ProductModel


class ProductViewModel constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val product = savedStateHandle.getLiveData<ProductModel>(PRODUCT_KEY)

    companion object {
        const val PRODUCT_KEY = "product"
    }

}