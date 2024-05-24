package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)