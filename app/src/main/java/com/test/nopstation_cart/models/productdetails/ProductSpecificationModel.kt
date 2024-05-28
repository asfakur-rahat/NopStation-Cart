package com.test.nopstation_cart.models.productdetails


import com.google.gson.annotations.SerializedName

data class ProductSpecificationModel(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("Groups")
    val groups: List<Group>
)