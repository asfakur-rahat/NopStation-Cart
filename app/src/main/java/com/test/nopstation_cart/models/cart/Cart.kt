package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("ButtonPaymentMethodViewComponents")
    val buttonPaymentMethodViewComponents: List<Any>,
    @SerializedName("CheckoutAttributes")
    val checkoutAttributes: List<CheckoutAttribute>,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX,
    @SerializedName("DiscountBox")
    val discountBox: DiscountBox,
    @SerializedName("DisplayTaxShippingInfo")
    val displayTaxShippingInfo: Boolean,
    @SerializedName("GiftCardBox")
    val giftCardBox: GiftCardBox,
    @SerializedName("HideCheckoutButton")
    val hideCheckoutButton: Boolean,
    @SerializedName("IsEditable")
    val isEditable: Boolean,
    @SerializedName("Items")
    val items: List<Item>,
    @SerializedName("MinOrderSubtotalWarning")
    val minOrderSubtotalWarning: Any?,
    @SerializedName("OnePageCheckoutEnabled")
    val onePageCheckoutEnabled: Boolean,
    @SerializedName("OrderReviewData")
    val orderReviewData: OrderReviewData,
    @SerializedName("ShowProductImages")
    val showProductImages: Boolean,
    @SerializedName("ShowSku")
    val showSku: Boolean,
    @SerializedName("ShowVendorName")
    val showVendorName: Boolean,
    @SerializedName("TermsOfServiceOnOrderConfirmPage")
    val termsOfServiceOnOrderConfirmPage: Boolean,
    @SerializedName("TermsOfServiceOnShoppingCartPage")
    val termsOfServiceOnShoppingCartPage: Boolean,
    @SerializedName("TermsOfServicePopup")
    val termsOfServicePopup: Boolean,
    @SerializedName("Warnings")
    val warnings: List<Any>
)