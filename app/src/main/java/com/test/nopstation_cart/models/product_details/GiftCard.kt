package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class GiftCard(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("GiftCardType")
    val giftCardType: Int,
    @SerializedName("IsGiftCard")
    val isGiftCard: Boolean,
    @SerializedName("Message")
    val message: Any,
    @SerializedName("RecipientEmail")
    val recipientEmail: Any,
    @SerializedName("RecipientName")
    val recipientName: Any,
    @SerializedName("SenderEmail")
    val senderEmail: Any,
    @SerializedName("SenderName")
    val senderName: Any
)