package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ImageSquaresPictureModel(
    @SerializedName("AlternateText")
    val alternateText: Any,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("FullSizeImageUrl")
    val fullSizeImageUrl: Any,
    @SerializedName("ImageUrl")
    val imageUrl: Any,
    @SerializedName("ThumbImageUrl")
    val thumbImageUrl: Any,
    @SerializedName("Title")
    val title: Any
)