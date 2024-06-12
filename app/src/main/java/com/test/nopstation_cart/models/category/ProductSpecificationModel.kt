package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class ProductSpecificationModel(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("Groups")
    val groups: @RawValue List<Any>
) : Parcelable