package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("AnonymousPermissed")
    val anonymousPermissed: Boolean,
    @SerializedName("Cart")
    val cart: Cart,
    @SerializedName("EstimateShipping")
    val estimateShipping: EstimateShipping,
    @SerializedName("OrderTotals")
    val orderTotals: OrderTotals,
    @SerializedName("SelectedCheckoutAttributes")
    val selectedCheckoutAttributes: String
)