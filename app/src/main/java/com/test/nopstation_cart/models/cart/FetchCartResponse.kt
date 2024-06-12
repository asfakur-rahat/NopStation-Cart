package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class FetchCartResponse(
    @SerializedName("Data")
    val `data`: DataX,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any?
)