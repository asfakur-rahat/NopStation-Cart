package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class DiscountBox(
    @SerializedName("AppliedDiscountsWithCodes")
    val appliedDiscountsWithCodes: List<Any>,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("Display")
    val display: Boolean,
    @SerializedName("IsApplied")
    val isApplied: Boolean,
    @SerializedName("Messages")
    val messages: List<Any>
)