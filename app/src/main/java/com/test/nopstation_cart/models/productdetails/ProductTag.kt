package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ProductTag(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ProductCount")
    val productCount: Int,
    @SerializedName("SeName")
    val seName: String
)