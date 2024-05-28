package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class Value(
    @SerializedName("ColorSquaresRgb")
    val colorSquaresRgb: Any?,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX?,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsPreSelected")
    val isPreSelected: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PriceAdjustment")
    val priceAdjustment: String?
)