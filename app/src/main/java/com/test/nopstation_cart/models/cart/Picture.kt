package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("AlternateText")
    val alternateText: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX?,
    @SerializedName("FullSizeImageUrl")
    val fullSizeImageUrl: Any?,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("ThumbImageUrl")
    val thumbImageUrl: Any?,
    @SerializedName("Title")
    val title: String
)