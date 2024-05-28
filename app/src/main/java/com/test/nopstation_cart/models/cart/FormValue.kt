package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class FormValue(
    @SerializedName("Key")
    val key: String,
    @SerializedName("Value")
    val value: String
)