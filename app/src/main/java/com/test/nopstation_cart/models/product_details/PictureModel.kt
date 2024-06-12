package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class PictureModel(
    @SerializedName("AlternateText")
    val alternateText: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("FullSizeImageUrl")
    val fullSizeImageUrl: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("ThumbImageUrl")
    val thumbImageUrl: String,
    @SerializedName("Title")
    val title: String
)