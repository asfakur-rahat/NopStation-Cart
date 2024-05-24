package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName

data class SubCategory(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Products")
    val products: List<Any>,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("SubCategories")
    val subCategories: List<Any>
)