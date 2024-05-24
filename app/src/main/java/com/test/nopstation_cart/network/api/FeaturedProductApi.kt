package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.featured_product.FeaturedProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeaturedProductApi {
    @GET("home/featureproducts")
    suspend fun getFeaturedProducts(): Response<FeaturedProductsResponse>
}