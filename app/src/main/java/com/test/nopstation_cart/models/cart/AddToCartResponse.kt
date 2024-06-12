package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: String
)