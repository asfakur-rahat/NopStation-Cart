package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ProductPrice(
    @SerializedName("BasePricePAngV")
    val basePricePAngV: Any,
    @SerializedName("BasePricePAngVValue")
    val basePricePAngVValue: Double,
    @SerializedName("CallForPrice")
    val callForPrice: Boolean,
    @SerializedName("CurrencyCode")
    val currencyCode: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("CustomerEntersPrice")
    val customerEntersPrice: Boolean,
    @SerializedName("DisplayTaxShippingInfo")
    val displayTaxShippingInfo: Boolean,
    @SerializedName("HidePrices")
    val hidePrices: Boolean,
    @SerializedName("IsRental")
    val isRental: Boolean,
    @SerializedName("OldPrice")
    val oldPrice: Any,
    @SerializedName("OldPriceValue")
    val oldPriceValue: Any,
    @SerializedName("Price")
    val price: String,
    @SerializedName("PriceValue")
    val priceValue: Double,
    @SerializedName("PriceWithDiscount")
    val priceWithDiscount: Any,
    @SerializedName("PriceWithDiscountValue")
    val priceWithDiscountValue: Any,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("RentalPrice")
    val rentalPrice: Any,
    @SerializedName("RentalPriceValue")
    val rentalPriceValue: Any
)