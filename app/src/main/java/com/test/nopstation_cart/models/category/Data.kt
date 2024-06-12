package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
@Parcelize
data class Data(
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @PrimaryKey
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Products")
    val products: List<Product>,
    @SerializedName("SeName")
    val seName: String,
    @SerializedName("SubCategories")
    val subCategories: List<SubCategory>
) : Parcelable