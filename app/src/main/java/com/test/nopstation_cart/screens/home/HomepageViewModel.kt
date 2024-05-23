package com.test.nopstation_cart.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.banner.BannerResponse
import com.test.nopstation_cart.models.banner.Data
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.BannerApi
import com.test.nopstation_cart.repository.BannerRepository
import kotlinx.coroutines.launch
import retrofit2.http.Header

class HomepageViewModel: ViewModel() {
    private val _banner : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val banner : MutableLiveData<Data>
        get() = _banner



    private val apiClient = ApiClient.getClient().create(BannerApi::class.java)
    private val repository = BannerRepository(apiClient)


    fun getBannerFromApi() = viewModelScope.launch{
        val response = repository.getBanner()
        if(response.isSuccessful){
            _banner.value = response.body()?.data
        }
    }

}