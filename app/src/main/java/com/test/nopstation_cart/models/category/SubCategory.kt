package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class SubCategory(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Products")
    val products: @RawValue List<Any>,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("SubCategories")
    val subCategories: @RawValue List<Any>
) : Parcelable