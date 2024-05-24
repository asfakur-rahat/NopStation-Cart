package com.test.nopstation_cart.screens.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.productdetails.Data
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.ProductApi
import com.test.nopstation_cart.repository.ProductDetailsRepository
import kotlinx.coroutines.launch

class ProductDetailsViewModel: ViewModel() {
    private val _product : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val product : MutableLiveData<Data>
        get() = _product


    private val apiClient = ApiClient.getClient().create(ProductApi::class.java)
    private val repository = ProductDetailsRepository(apiClient)

    fun getProductDetails(productID: Int) = viewModelScope.launch {
        val response = repository.getProductDetails(productID)

        if (response.isSuccessful) {
            _product.value = response.body()?.data
        }
    }

}