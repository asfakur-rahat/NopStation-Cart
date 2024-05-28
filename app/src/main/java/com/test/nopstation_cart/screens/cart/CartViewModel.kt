package com.test.nopstation_cart.screens.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.FetchCartResponse
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.models.cart.Item
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.CartApi
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.repository.PreferenceRepository
import kotlinx.coroutines.launch

class CartViewModel(
    private val preferenceRepository: PreferenceRepository
): ViewModel() {
    private val _cartList: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val cartList: LiveData<FetchCartResponse>
        get() = _cartList

    private val apiClient = ApiClient.getClient(preferenceRepository.getToken()).create(CartApi::class.java)
    private val repository = CartRepository(apiClient)

    fun fetchCart() =  viewModelScope.launch{
        val response = repository.FetchCartItems()
        if (response.isSuccessful) {
            _cartList.value = response.body()
        }
    }

    private val _isCancle: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val isCancle: LiveData<FetchCartResponse>
        get() = _isCancle

    fun removeItem(item: Item)  = viewModelScope.launch{
            val response = repository.updateCart(
                AddToCartRequest(
                    listOf(
                        FormValue(
                            key = "removefromcart",
                            value = item.id.toString()
                        )
                    )
                )
            )
            if (response.isSuccessful) {
                _isCancle.value = response.body()
            }
    }

    private val _quantityUpdated: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val quantityUpdated: LiveData<FetchCartResponse>
        get() = _quantityUpdated

    fun updateQuantity(item: Item, quantity: Int)  = viewModelScope.launch {
        val response = repository.updateCart(
            AddToCartRequest(
                listOf(
                    FormValue(
                        key = "itemquantity${item.id}",
                        value = quantity.toString()
                    )
                )
            )
        )
        if (response.isSuccessful){
            _quantityUpdated.value = response.body()
        }
    }
}