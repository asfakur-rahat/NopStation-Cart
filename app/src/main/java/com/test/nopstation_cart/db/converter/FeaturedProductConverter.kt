package com.test.nopstation_cart.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.nopstation_cart.models.featured_product.CustomProperties
import com.test.nopstation_cart.models.featured_product.PictureModel
import com.test.nopstation_cart.models.featured_product.ProductPrice
import com.test.nopstation_cart.models.featured_product.ProductSpecificationModel
import com.test.nopstation_cart.models.featured_product.ReviewOverviewModel

class FeaturedProductConverter {
    @TypeConverter
    fun fromPictureModelList(value: List<PictureModel>): String {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toPictureModel(value: String): List<PictureModel> {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductPrice(value: ProductPrice): String {
        val type = object : TypeToken<ProductPrice>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductPrice(value: String): ProductPrice {
        val type = object : TypeToken<ProductPrice>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductSpecification(value: ProductSpecificationModel): String {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductSpecification(value: String): ProductSpecificationModel {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromReviewOverviewModel(value: ReviewOverviewModel): String {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toReviewOverviewModel(value: String): ReviewOverviewModel {
        val type = object : TypeToken<ReviewOverviewModel>() {}.type
        return Gson().fromJson(value, type)
    }
    @TypeConverter
    fun fromCustomProperties(value: CustomProperties): String {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toCustomProperties(value: String): CustomProperties {
        val type = object : TypeToken<CustomProperties>() {}.type
        return Gson().fromJson(value, type)
    }
}