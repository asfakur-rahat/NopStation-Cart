package com.test.nopstation_cart.models.account


import com.google.gson.annotations.SerializedName

data class UserAccountResponse(
    @SerializedName("Data")
    val `data`: Data,
    @SerializedName("ErrorList")
    val errorList: List<Any>,
    @SerializedName("Message")
    val message: Any?
)