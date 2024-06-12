package com.test.nopstation_cart.models.Auth


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("CustomerInfo")
    val customerInfo: CustomerInfo,
    @SerializedName("Token")
    val token: String
)