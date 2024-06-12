package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class VendorModel(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: Any,
    @SerializedName("SeName")
    val seName: Any
)