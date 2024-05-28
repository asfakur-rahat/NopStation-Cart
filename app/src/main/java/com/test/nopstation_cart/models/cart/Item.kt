package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("AllowItemEditing")
    val allowItemEditing: Boolean,
    @SerializedName("AllowedQuantities")
    val allowedQuantities: List<Any>,
    @SerializedName("AttributeInfo")
    val attributeInfo: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX?,
    @SerializedName("DisableRemoval")
    val disableRemoval: Boolean,
    @SerializedName("Discount")
    val discount: Any?,
    @SerializedName("DiscountValue")
    val discountValue: Double,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("MaximumDiscountedQty")
    val maximumDiscountedQty: Any?,
    @SerializedName("Picture")
    val picture: Picture,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("ProductName")
    val productName: String,
    @SerializedName("ProductSeName")
    val productSeName: String,
    @SerializedName("Quantity")
    val quantity: Int,
    @SerializedName("RecurringInfo")
    val recurringInfo: Any?,
    @SerializedName("RentalInfo")
    val rentalInfo: Any?,
    @SerializedName("Sku")
    val sku: String,
    @SerializedName("SubTotal")
    val subTotal: String,
    @SerializedName("SubTotalValue")
    val subTotalValue: Double,
    @SerializedName("UnitPrice")
    val unitPrice: String,
    @SerializedName("UnitPriceValue")
    val unitPriceValue: Double,
    @SerializedName("VendorName")
    val vendorName: String,
    @SerializedName("Warnings")
    val warnings: List<Any>
)