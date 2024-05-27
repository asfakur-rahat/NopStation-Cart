package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("Allow")
    val allow: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Height")
    val height: Int,
    @SerializedName("VideoUrl")
    val videoUrl: String,
    @SerializedName("Width")
    val width: Int
)