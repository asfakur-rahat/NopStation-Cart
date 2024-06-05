package com.test.nopstation_cart.repository

import android.content.SharedPreferences
import com.test.nopstation_cart.models.product_details.ProductDetailsResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val api: ProductApi
){
    suspend fun getProductDetails(id: Int): Response<ProductDetailsResponse> = withContext(Dispatchers.IO){
        return@withContext api.getProductDetails(id)
    }
}