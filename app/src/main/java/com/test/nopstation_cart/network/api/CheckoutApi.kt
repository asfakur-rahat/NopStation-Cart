package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.checkout.OrderResponse
import retrofit2.Response
import retrofit2.http.GET

interface CheckoutApi {
    @GET("confirmorder")
    suspend fun placeOrder(): Response<OrderResponse>
}