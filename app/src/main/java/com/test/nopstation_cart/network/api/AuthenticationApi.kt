package com.test.nopstation_cart.network.api

import com.test.nopstation_cart.models.Auth.Login
import com.test.nopstation_cart.models.Auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("customer/login")
    suspend fun userLogin(@Body request: Login): Response<LoginResponse>
}