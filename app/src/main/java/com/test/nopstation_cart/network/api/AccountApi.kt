package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.account.UserAccountResponse
import retrofit2.Response
import retrofit2.http.GET

interface AccountApi {
    @GET("customer/info")
    suspend fun getAccountInfo(): Response<UserAccountResponse>
}