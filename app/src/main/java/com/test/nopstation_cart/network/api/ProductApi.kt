package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.productdetails.ProductDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("product/productdetails/{id}")
    suspend fun getProductDetails(@Path("id") productID: Int): Response<ProductDetailsResponse>
}