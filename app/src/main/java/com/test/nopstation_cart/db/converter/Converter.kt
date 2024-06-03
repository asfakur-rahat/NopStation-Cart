package com.test.nopstation_cart.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.nopstation_cart.models.category.CustomProperties
import com.test.nopstation_cart.models.category.PictureModel
import com.test.nopstation_cart.models.category.Product
import com.test.nopstation_cart.models.category.ProductPrice
import com.test.nopstation_cart.models.category.ProductSpecificationModel
import com.test.nopstation_cart.models.category.ReviewOverviewModel
import com.test.nopstation_cart.models.category.SubCategory

class Converter {
    //for Category table
    @TypeConverter
    fun fromProductList(value: List<Product>): String {
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductList(value: String): List<Product> {
        val type = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromSubCategoryList(value: List<SubCategory>): String {
        val type = object : TypeToken<List<SubCategory>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toSubCategoryList(value: String): List<SubCategory> {
        val type = object : TypeToken<List<SubCategory>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromPictureModelList(value: List<PictureModel>): String {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toPictureModelList(value: String): List<PictureModel> {
        val type = object : TypeToken<List<PictureModel>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductPriceList(value: List<ProductPrice>): String {
        val type = object : TypeToken<List<ProductPrice>>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductPriceList(value: String): List<ProductPrice> {
        val type = object : TypeToken<List<ProductPrice>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromProductSpecificationModel(value: ProductSpecificationModel): String {
        val type = object : TypeToken<ProductSpecificationModel>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toProductSpecificationModel(value: String): ProductSpecificationModel {
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