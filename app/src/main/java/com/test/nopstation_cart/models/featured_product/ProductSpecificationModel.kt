package com.test.nopstation_cart.models.featured_product


import com.google.gson.annotations.SerializedName

data class ProductSpecificationModel(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties,
    @SerializedName("Groups")
    val groups: List<Any>
)