package com.test.nopstation_cart.network

import com.test.nopstation_cart.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private fun buildClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }).addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Content-Type", Constants.CONTENT_TYPE)
                        .addHeader("DeviceId", Constants.DEVICE_ID)
                        .addHeader("NST", Constants.NST)
                        .addHeader("User-Agent", Constants.USER_AGENT)
                        .build()

                    chain.proceed(newRequest)
                }.build()
        }

        fun getClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}