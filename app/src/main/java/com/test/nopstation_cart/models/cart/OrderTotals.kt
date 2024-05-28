package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class OrderTotals(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("DisplayTax")
    val displayTax: Boolean,
    @SerializedName("DisplayTaxRates")
    val displayTaxRates: Boolean,
    @SerializedName("GiftCards")
    val giftCards: List<Any>,
    @SerializedName("HideShippingTotal")
    val hideShippingTotal: Boolean,
    @SerializedName("IsEditable")
    val isEditable: Boolean,
    @SerializedName("OrderTotal")
    val orderTotal: String,
    @SerializedName("OrderTotalDiscount")
    val orderTotalDiscount: String,
    @SerializedName("PaymentMethodAdditionalFee")
    val paymentMethodAdditionalFee: Any?,
    @SerializedName("RedeemedRewardPoints")
    val redeemedRewardPoints: Int,
    @SerializedName("RedeemedRewardPointsAmount")
    val redeemedRewardPointsAmount: Any?,
    @SerializedName("RequiresShipping")
    val requiresShipping: Boolean,
    @SerializedName("SelectedShippingMethod")
    val selectedShippingMethod: Any?,
    @SerializedName("Shipping")
    val shipping: String,
    @SerializedName("SubTotal")
    val subTotal: String,
    @SerializedName("SubTotalDiscount")
    val subTotalDiscount: Any?,
    @SerializedName("Tax")
    val tax: String,
    @SerializedName("TaxRates")
    val taxRates: List<Any>,
    @SerializedName("WillEarnRewardPoints")
    val willEarnRewardPoints: Int
)