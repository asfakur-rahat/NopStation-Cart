package com.test.nopstation_cart.models

data class ProductItems (
    val id: Int,
    val productName: String,
    val productImage: String,
    val productPrice: Double,
    var productRating: Float
)