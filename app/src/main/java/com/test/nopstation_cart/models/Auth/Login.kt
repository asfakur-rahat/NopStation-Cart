package com.test.nopstation_cart.models.Auth


import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("Data")
    val `data`: DataX,
    @SerializedName("FormValues")
    val formValues: List<Any> = emptyList(),
    @SerializedName("UploadPicture")
    val uploadPicture: UploadPicture = UploadPicture()
)