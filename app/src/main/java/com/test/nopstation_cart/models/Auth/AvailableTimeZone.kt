package com.test.nopstation_cart.models.Auth


import com.google.gson.annotations.SerializedName

data class AvailableTimeZone(
    @SerializedName("Disabled")
    val disabled: Boolean,
    @SerializedName("Group")
    val group: Any,
    @SerializedName("Selected")
    val selected: Boolean,
    @SerializedName("Text")
    val text: String,
    @SerializedName("Value")
    val value: String
)