package com.test.nopstation_cart.models.category


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ReviewOverviewModel(
    @SerializedName("AllowCustomerReviews")
    val allowCustomerReviews: Boolean,
    @SerializedName("CanAddNewReview")
    val canAddNewReview: Boolean,
    @SerializedName("CustomProperties")
    val customProperties: CustomProperties?,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("RatingSum")
    val ratingSum: Int,
    @SerializedName("TotalReviews")
    val totalReviews: Int
) : Parcelable