package com.test.nopstation_cart.screens.productdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.AddToCartResponse
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.models.productdetails.Data
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.CartApi
import com.test.nopstation_cart.network.api.ProductApi
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.repository.PreferenceRepository
import com.test.nopstation_cart.repository.ProductDetailsRepository
import kotlinx.coroutines.launch

class ProductDetailsViewModel(private val preferenceRepository: PreferenceRepository): ViewModel() {
    private val _product : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val product : MutableLiveData<Data>
        get() = _product


    private val apiClient = ApiClient.getClient(null).create(ProductApi::class.java)
    private val repository = ProductDetailsRepository(apiClient)




    fun getProductDetails(productID: Int) = viewModelScope.launch {
        val response = repository.getProductDetails(productID)

        if (response.isSuccessful) {
            _product.value = response.body()?.data
        }
    }

    private val _cartProducts: MutableLiveData<AddToCartResponse> by lazy {
        MutableLiveData<AddToCartResponse>()
    }
    val cartProducts: MutableLiveData<AddToCartResponse>
        get() = _cartProducts

    private var token: String? = preferenceRepository.getToken()



    private val cartApi = ApiClient.getClient(token).create(CartApi::class.java)
    private val cartRepository = CartRepository(cartApi)

    fun addToCart(productID: Int, quantity: Int = 1) = viewModelScope.launch {
        println(token)
        val request = AddToCartRequest(listOf(
            FormValue(
            key = "addtocart_12020.EnteredQuantity",
            value = "$quantity"
        ),FormValue(
            key = "addtocart_12020.EnteredGender",
            value = "male"
        )))
        val response = cartRepository.AddToCart(productID, request)
        if (response.isSuccessful) {
            _cartProducts.value = response.body()
        }
    }
}