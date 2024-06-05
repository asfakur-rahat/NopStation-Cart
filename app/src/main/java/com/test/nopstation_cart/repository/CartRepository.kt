package com.test.nopstation_cart.repository

import android.content.SharedPreferences
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.AddToCartResponse
import com.test.nopstation_cart.models.cart.FetchCartResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.CartApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class CartRepository @Inject constructor(
    private  val api: CartApi
) {

    suspend fun addToCart(productID: Int, request: AddToCartRequest): Response<AddToCartResponse> = withContext(Dispatchers.IO){
        return@withContext api.addProductToCart(productID, request)
    }

    suspend fun updateCart(request: AddToCartRequest) : Response<FetchCartResponse> = withContext(Dispatchers.IO){
        return@withContext api.updateCart(request)
    }

    suspend fun fetchCartItems(): Response<FetchCartResponse> = withContext(Dispatchers.IO){
        return@withContext api.fetchCartItems()
    }
}