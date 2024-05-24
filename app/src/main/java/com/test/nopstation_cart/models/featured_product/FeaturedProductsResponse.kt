package com.test.nopstation_cart.models.featured_product


import com.google.gson.annotations.SerializedName

data class FeaturedProductsResponse(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)