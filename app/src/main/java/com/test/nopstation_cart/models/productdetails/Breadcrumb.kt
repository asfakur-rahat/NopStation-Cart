package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class Breadcrumb(
    @SerializedName("CategoryBreadcrumb")
    val categoryBreadcrumb: List<CategoryBreadcrumb>,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Enabled")
    val enabled: Boolean,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("ProductName")
    val productName: String,
    @SerializedName("ProductSeName")
    val productSeName: String
)