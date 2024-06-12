package com.test.nopstation_cart.repository


import android.content.SharedPreferences
import com.test.nopstation_cart.models.Auth.Login
import com.test.nopstation_cart.models.Auth.LoginResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.AuthenticationApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: AuthenticationApi
)
{

    suspend fun userLogin(request: Login): Response<LoginResponse> = withContext(Dispatchers.IO){
        return@withContext api.userLogin(request)
    }
}