package com.test.nopstation_cart.repository

import com.test.nopstation_cart.models.category.CategoryResponse
import com.test.nopstation_cart.models.featured_product.FeaturedProductsResponse
import com.test.nopstation_cart.network.api.FeaturedProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class FeaturedProductRepository(
    private val api: FeaturedProductApi
){
    suspend fun getFeaturedProducts(): Response<FeaturedProductsResponse> = withContext(Dispatchers.IO){
        return@withContext api.getFeaturedProducts()
    }

    suspend fun getCategoryWithProducts(): Response<CategoryResponse> = withContext(Dispatchers.IO){
        return@withContext api.getCategoriesWithProducts()
    }
}