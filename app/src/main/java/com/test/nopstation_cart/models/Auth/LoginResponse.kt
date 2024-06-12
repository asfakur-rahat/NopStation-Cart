package com.test.nopstation_cart.models.Auth


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any
)