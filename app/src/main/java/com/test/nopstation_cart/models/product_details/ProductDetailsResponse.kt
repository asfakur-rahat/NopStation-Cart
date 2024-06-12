package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)