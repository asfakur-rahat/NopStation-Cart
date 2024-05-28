package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class OrderReviewData(
    @SerializedName("BillingAddress")
    val billingAddress: BillingAddress,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("CustomValues")
    val customValues: CustomValues,
    @SerializedName("Display")
    val display: Boolean,
    @SerializedName("IsShippable")
    val isShippable: Boolean,
    @SerializedName("PaymentMethod")
    val paymentMethod: Any?,
    @SerializedName("PickupAddress")
    val pickupAddress: PickupAddress,
    @SerializedName("SelectedPickupInStore")
    val selectedPickupInStore: Boolean,
    @SerializedName("ShippingAddress")
    val shippingAddress: ShippingAddress,
    @SerializedName("ShippingMethod")
    val shippingMethod: Any?
)