package com.test.nopstation_cart.models.Auth


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("CheckoutAsGuest")
    val checkoutAsGuest: Boolean = false,
    @SerializedName("DisplayCaptcha")
    val displayCaptcha: Boolean = false,
    @SerializedName("Email")
    val email: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("RegistrationType")
    val registrationType: Int = 2,
    @SerializedName("RememberMe")
    val rememberMe: Boolean = false,
    @SerializedName("UsernamesEnabled")
    val usernamesEnabled: Boolean = false
)