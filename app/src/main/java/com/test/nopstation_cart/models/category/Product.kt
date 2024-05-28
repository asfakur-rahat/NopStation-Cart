package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Product(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("FullDescription")
    val fullDescription: String?,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("MarkAsNew")
    val markAsNew: Boolean,
    @SerializedName("Name")
    val name: String,
    @SerializedName("PictureModels")
    val pictureModels: List<PictureModel>,
    @SerializedName("ProductPrice")
    val productPrice: ProductPrice,
    @SerializedName("ProductSpecificationModel")
    val productSpecificationModel: ProductSpecificationModel,
    @SerializedName("ProductType")
    val productType: Int,
    @SerializedName("ReviewOverviewModel")
    val reviewOverviewModel: ReviewOverviewModel,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("ShortDescription")
    val shortDescription: String?,
    @SerializedName("Sku")
    val sku: String
) : Parcelable