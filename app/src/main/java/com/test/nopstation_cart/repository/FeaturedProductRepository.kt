package com.test.nopstation_cart.repository

import android.content.SharedPreferences
import com.test.nopstation_cart.db.AppDatabase
import com.test.nopstation_cart.models.category.CategoryResponse
import com.test.nopstation_cart.models.featured_product.FeaturedProductsResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.FeaturedProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FeaturedProductRepository @Inject constructor(
    private val api: FeaturedProductApi,
    private val db: AppDatabase
){
    suspend fun getFeaturedProducts() = withContext(Dispatchers.IO){
        val response =  api.getFeaturedProducts()
        if (response.isSuccessful){
            db.featuredDao().saveFeaturedProducts(response.body()?.data!!)
        }
        return@withContext response
    }

    suspend fun getFeaturedProductsFromRoom() = withContext(Dispatchers.IO){
        return@withContext db.featuredDao().getFeaturedProducts()
    }

    suspend fun getCategoryWithProducts(): Response<CategoryResponse> = withContext(Dispatchers.IO){
        val response = api.getCategoriesWithProducts()
        if (response.isSuccessful){
            db.categoryDao().insertCategory(response.body()!!.data)
        }
        return@withContext response
    }

    suspend fun getCategoriesFromRoom() = withContext(Dispatchers.IO){
        return@withContext db.categoryDao().getCategory()
    }
}