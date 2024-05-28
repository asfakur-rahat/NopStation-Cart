package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.AddToCartResponse
import com.test.nopstation_cart.models.cart.FetchCartResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {
    @POST("shoppingCart/AddProductToCart/details/{id}/1")
    suspend fun addProductToCart(@Path("id") productID: Int, @Body request: AddToCartRequest) : Response<AddToCartResponse>

    @POST("shoppingcart/updatecart")
    suspend fun updateCart(@Body request: AddToCartRequest) : Response<FetchCartResponse>

    @GET("shoppingcart/cart")
    suspend fun fetchCartItems() : Response<FetchCartResponse>
}