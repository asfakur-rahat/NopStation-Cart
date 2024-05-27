package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("ColorSquaresRgb")
    val colorSquaresRgb: Any,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("CustomerEntersQty")
    val customerEntersQty: Boolean,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("ImageSquaresPictureModel")
    val imageSquaresPictureModel: ImageSquaresPictureModel,
    @SerializedName("IsPreSelected")
    val isPreSelected: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PictureId")
    val pictureId: Int,
    @SerializedName("PriceAdjustment")
    val priceAdjustment: String,
    @SerializedName("PriceAdjustmentUsePercentage")
    val priceAdjustmentUsePercentage: Boolean,
    @SerializedName("PriceAdjustmentValue")
    val priceAdjustmentValue: Double,
    @SerializedName("Quantity")
    val quantity: Int
)