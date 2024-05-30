package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class ProductAttribute(
    @SerializedName("AllowedFileExtensions")
    val allowedFileExtensions: List<Any>,
    @SerializedName("AttributeControlType")
    val attributeControlType: Int,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("DefaultValue")
    val defaultValue: Any,
    @SerializedName("Description")
    val description: Any,
    @SerializedName("HasCondition")
    val hasCondition: Boolean,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsRequired")
    val isRequired: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ProductAttributeId")
    val productAttributeId: Int,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("SelectedDay")
    val selectedDay: Any,
    @SerializedName("SelectedMonth")
    val selectedMonth: Any,
    @SerializedName("SelectedYear")
    val selectedYear: Any,
    @SerializedName("TextPrompt")
    val textPrompt: Any,
    @SerializedName("Values")
    val values: List<Value>
)