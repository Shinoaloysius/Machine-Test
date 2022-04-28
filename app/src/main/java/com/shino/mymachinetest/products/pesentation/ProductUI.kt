package com.shino.mymachinetest.products.pesentation

sealed class ProductUI {
    object Loading : ProductUI()
    object Idle : ProductUI()
//    data class ProductSuccess(val addresses : SaveAddressRes) : ProductUI()
    data class Error(val message :String): ProductUI()
}