package com.test.nopstation_cart.models

data class BestSellingItem(
    val id: Int,
    val productName: String,
    val productImage: Int,
    val productPrice: Double,
    var productRating: Float
)
