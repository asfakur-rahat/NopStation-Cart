package com.test.nopstation_cart.repository

import com.test.nopstation_cart.models.productdetails.ProductDetailsResponse
import com.test.nopstation_cart.network.api.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductDetailsRepository(
    private val api: ProductApi
){
    suspend fun getProductDetails(id: Int): Response<ProductDetailsResponse> = withContext(Dispatchers.IO){
        return@withContext api.getProductDetails(id)
    }
}