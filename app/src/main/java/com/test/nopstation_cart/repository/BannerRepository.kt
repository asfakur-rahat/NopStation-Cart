package com.test.nopstation_cart.repository

import com.test.nopstation_cart.db.AppDatabase
import com.test.nopstation_cart.network.api.BannerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BannerRepository @Inject constructor(
    private val bannerApi: BannerApi,
    private val db: AppDatabase
) {
    suspend fun getBanner() = withContext(Dispatchers.IO){
        val bannerResponse = bannerApi.getBanner()

        bannerResponse.let {
            bannerResponse.body()?.data?.sliders?.let {slider ->
                db.bannerDao().saveBannerList(slider)
            }
        }

        return@withContext bannerResponse
    }

    suspend fun getBannerFromRoom() = withContext(Dispatchers.IO){
        return@withContext db.bannerDao().getBannerList()
    }

}