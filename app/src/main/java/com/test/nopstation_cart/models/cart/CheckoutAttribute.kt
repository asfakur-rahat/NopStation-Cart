package com.test.nopstation_cart.models.cart


import com.google.gson.annotations.SerializedName

data class CheckoutAttribute(
    @SerializedName("AllowedFileExtensions")
    val allowedFileExtensions: List<Any>,
    @SerializedName("AttributeControlType")
    val attributeControlType: Int,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXX?,
    @SerializedName("DefaultValue")
    val defaultValue: Any?,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("IsRequired")
    val isRequired: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("SelectedDay")
    val selectedDay: Any?,
    @SerializedName("SelectedMonth")
    val selectedMonth: Any?,
    @SerializedName("SelectedYear")
    val selectedYear: Any?,
    @SerializedName("TextPrompt")
    val textPrompt: Any?,
    @SerializedName("Values")
    val values: List<Value>
)