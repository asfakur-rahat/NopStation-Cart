package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class CategoryBreadcrumb(
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("HaveSubCategories")
    val haveSubCategories: Boolean,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IncludeInTopMenu")
    val includeInTopMenu: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("NumberOfProducts")
    val numberOfProducts: Any,
    @SerializedName("Route")
    val route: Any,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("SubCategories")
    val subCategories: List<Any>
)