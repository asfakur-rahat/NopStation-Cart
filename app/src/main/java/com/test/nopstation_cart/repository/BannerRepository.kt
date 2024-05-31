package com.test.nopstation_cart.repository

import com.test.nopstation_cart.models.banner.BannerResponse
import com.test.nopstation_cart.network.api.BannerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class BannerRepository @Inject constructor(
    private val bannerApi: BannerApi
) {
    suspend fun getBanner() : Response<BannerResponse> = withContext(Dispatchers.IO){
        return@withContext bannerApi.getBanner()
    }
}