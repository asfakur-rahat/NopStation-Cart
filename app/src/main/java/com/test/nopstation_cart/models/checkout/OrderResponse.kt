package com.test.nopstation_cart.models.checkout


import com.google.gson.annotations.SerializedName

data class OrderResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("orderId")
    val orderId: String
)