package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)