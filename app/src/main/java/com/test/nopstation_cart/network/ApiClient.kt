package com.test.nopstation_cart.network

import android.content.SharedPreferences
import com.test.nopstation_cart.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private fun buildClient(sharedPreferences: SharedPreferences): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }).addInterceptor { chain ->

                    val builder = chain.request().newBuilder()
                    val token = sharedPreferences.getString("Token", null)
                    if(token != null){
                        println("API With Token")
                        builder.addHeader("Token", token)
                    }else{
                        println("API Without Token")
                    }
                    val newRequest = builder
                        .addHeader("Content-Type", Constants.CONTENT_TYPE)
                        .addHeader("DeviceId", Constants.DEVICE_ID)
                        .addHeader("NST", Constants.NST)
                        .addHeader("User-Agent", Constants.USER_AGENT)
                        .build()
                    chain.proceed(newRequest)
                }.build()
        }
        fun getClient(sharedPreferences: SharedPreferences): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(buildClient(sharedPreferences))
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

    }
}