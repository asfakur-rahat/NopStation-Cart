package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class DefaultPictureModel(
    @SerializedName("AlternateText")
    val alternateText: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("FullSizeImageUrl")
    val fullSizeImageUrl: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("ThumbImageUrl")
    val thumbImageUrl: Any,
    @SerializedName("Title")
    val title: String
)