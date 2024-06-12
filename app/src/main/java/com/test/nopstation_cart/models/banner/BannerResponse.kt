package com.test.nopstation_cart.models.banner


import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)