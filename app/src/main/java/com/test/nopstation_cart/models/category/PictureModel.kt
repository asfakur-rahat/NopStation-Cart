package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class PictureModel(
    @SerializedName("AlternateText")
    val alternateText: String,
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("FullSizeImageUrl")
    val fullSizeImageUrl: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    @SerializedName("ThumbImageUrl")
    val thumbImageUrl: @RawValue Any?,
    @SerializedName("Title")
    val title: String
) : Parcelable