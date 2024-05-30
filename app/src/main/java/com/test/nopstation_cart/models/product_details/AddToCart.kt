package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class AddToCart(
    @SerializedName("AllowedQuantities")
    val allowedQuantities: List<Any>,
    @SerializedName("AvailableForPreOrder")
    val availableForPreOrder: Boolean,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("CustomerEnteredPrice")
    val customerEnteredPrice: Double,
    @SerializedName("CustomerEnteredPriceRange")
    val customerEnteredPriceRange: Any,
    @SerializedName("CustomerEntersPrice")
    val customerEntersPrice: Boolean,
    @SerializedName("DisableBuyButton")
    val disableBuyButton: Boolean,
    @SerializedName("DisableWishlistButton")
    val disableWishlistButton: Boolean,
    @SerializedName("EnteredQuantity")
    val enteredQuantity: Int,
    @SerializedName("IsRental")
    val isRental: Boolean,
    @SerializedName("MinimumQuantityNotification")
    val minimumQuantityNotification: Any,
    @SerializedName("PreOrderAvailabilityStartDateTimeUserTime")
    val preOrderAvailabilityStartDateTimeUserTime: Any,
    @SerializedName("PreOrderAvailabilityStartDateTimeUtc")
    val preOrderAvailabilityStartDateTimeUtc: Any,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("UpdateShoppingCartItemType")
    val updateShoppingCartItemType: Any,
    @SerializedName("UpdatedShoppingCartItemId")
    val updatedShoppingCartItemId: Int
)