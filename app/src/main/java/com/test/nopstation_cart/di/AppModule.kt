package com.test.nopstation_cart.di

import android.content.Context
import android.content.SharedPreferences
import com.test.nopstation_cart.db.AppDatabase
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.AccountApi
import com.test.nopstation_cart.network.api.AuthenticationApi
import com.test.nopstation_cart.network.api.BannerApi
import com.test.nopstation_cart.network.api.CartApi
import com.test.nopstation_cart.network.api.FeaturedProductApi
import com.test.nopstation_cart.network.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideApi(sharedPreferences: SharedPreferences): Retrofit {
        return ApiClient.getClient(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBannersApi(retrofit: Retrofit): BannerApi {
        return retrofit.create(BannerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFeaturedProductsApi(retrofit: Retrofit): FeaturedProductApi {
        return retrofit.create(FeaturedProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartApi(retrofit: Retrofit): CartApi {
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }
}