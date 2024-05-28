package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("Attributes")
    val attributes: List<Any>,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: Any
)