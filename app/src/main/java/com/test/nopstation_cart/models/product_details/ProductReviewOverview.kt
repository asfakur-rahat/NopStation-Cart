package com.test.nopstation_cart.models.product_details


import com.google.gson.annotations.SerializedName

data class ProductReviewOverview(
    @SerializedName("AllowCustomerReviews")
    val allowCustomerReviews: Boolean,
    @SerializedName("CanAddNewReview")
    val canAddNewReview: Boolean,
    @SerializedName("CustomProperties")
    val customProperties: CustomPropertiesXXX,
    @SerializedName("ProductId")
    val productId: Int,
    @SerializedName("RatingSum")
    val ratingSum: Int,
    @SerializedName("TotalReviews")
    val totalReviews: Int
)