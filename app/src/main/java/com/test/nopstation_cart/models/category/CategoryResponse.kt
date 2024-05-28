package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class CategoryResponse(
    @SerializedName("Data")
    val `data`: List<Data>,
    @SerializedName("ErrorList")
    val errorList: @RawValue List<Any>,
    @SerializedName("Message")
    val message: @RawValue Any?
) : Parcelable