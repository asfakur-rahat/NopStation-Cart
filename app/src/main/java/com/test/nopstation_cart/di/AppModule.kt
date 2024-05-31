package com.test.nopstation_cart.di

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.AuthenticationApi
import com.test.nopstation_cart.network.api.BannerApi
import com.test.nopstation_cart.network.api.CartApi
import com.test.nopstation_cart.network.api.FeaturedProductApi
import com.test.nopstation_cart.network.api.ProductApi
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.utils.CartItemCountViewModel
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
    fun provideOnlineStatus(@ApplicationContext context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideToken(sharedPreferences: SharedPreferences): String? {
        return sharedPreferences.getString("token", null)
    }

    @Provides
    @Singleton
    fun provideApiWithToken(token: String?): Retrofit {
        if (token == null) {
            return ApiClient.getClient2()
        }
        return ApiClient.getClient(token)
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBannersApi(retrofit: Retrofit): BannerApi{
        return retrofit.create(BannerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFeaturedProductsApi(retrofit: Retrofit): FeaturedProductApi{
        return retrofit.create(FeaturedProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartApi(retrofit: Retrofit): CartApi{
        return retrofit.create(CartApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCartItemCountViewModel(repository: CartRepository): CartItemCountViewModel {
        return CartItemCountViewModel(repository)
    }
}