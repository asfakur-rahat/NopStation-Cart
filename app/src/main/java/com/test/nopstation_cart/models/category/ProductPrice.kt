package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductPrice(
    @SerializedName("AvailableForPreOrder")
    val availableForPreOrder: Boolean,
    @SerializedName("BasePricePAngV")
    val basePricePAngV: @RawValue Any?,
    @SerializedName("BasePricePAngVValue")
    val basePricePAngVValue: Double?,
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("DisableAddToCompareListButton")
    val disableAddToCompareListButton: Boolean,
    @SerializedName("DisableBuyButton")
    val disableBuyButton: Boolean,
    @SerializedName("DisableWishlistButton")
    val disableWishlistButton: Boolean,
    @SerializedName("DisplayTaxShippingInfo")
    val displayTaxShippingInfo: Boolean,
    @SerializedName("ForceRedirectionAfterAddingToCart")
    val forceRedirectionAfterAddingToCart: Boolean,
    @SerializedName("IsRental")
    val isRental: Boolean,
    @SerializedName("OldPrice")
    val oldPrice: String?,
    @SerializedName("OldPriceValue")
    val oldPriceValue: @RawValue Any?,
    @SerializedName("PreOrderAvailabilityStartDateTimeUtc")
    val preOrderAvailabilityStartDateTimeUtc: @RawValue Any?,
    @SerializedName("Price")
    val price: String?,
    @SerializedName("PriceValue")
    val priceValue: Double?
) : Parcelable