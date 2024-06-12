package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.banner.BannerResponse
import retrofit2.Response
import retrofit2.http.GET

interface BannerApi {
    @GET("slider/homepageslider")
    suspend fun getBanner(): Response<BannerResponse>
}