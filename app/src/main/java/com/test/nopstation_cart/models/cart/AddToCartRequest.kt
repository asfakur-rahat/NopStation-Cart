package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class AddToCartRequest(
    @SerializedName("FormValues")
    val formValues: List<FormValue>
)