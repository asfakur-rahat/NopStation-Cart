package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class GiftCardBox(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("Display")
    val display: Boolean,
    @SerializedName("IsApplied")
    val isApplied: Boolean,
    @SerializedName("Message")
    val message: Any?
)