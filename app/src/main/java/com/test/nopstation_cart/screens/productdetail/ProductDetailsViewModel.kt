package com.test.nopstation_cart.screens.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.AddToCartResponse
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.models.product_details.Data
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.repository.ProductDetailsRepository
import com.test.nopstation_cart.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductDetailsRepository,
    private val cartRepository: CartRepository
    ): ViewModel() {
    private val _product : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val product : LiveData<Data>
        get() = _product

    fun getProductDetails(productID: Int) = viewModelScope.launch {
        val response = repository.getProductDetails(productID)

        if (response.isSuccessful) {
            _product.value = response.body()?.data
        }
    }

    private val _cartProducts: MutableLiveData<Event<AddToCartResponse>> by lazy {
        MutableLiveData<Event<AddToCartResponse>>()
    }
    val cartProducts: LiveData<Event<AddToCartResponse>>
        get() = _cartProducts

    private val _trigger: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val trigger: LiveData<Boolean>
        get() = _trigger

    fun addToCart(productID: Int, quantity: Int = 1) = viewModelScope.launch {
        val request = AddToCartRequest(listOf(
            FormValue(
            key = "addtocart_${productID}.EnteredQuantity",
            value = "$quantity"
        )))
        val response = cartRepository.AddToCart(productID, request)
        if (response.isSuccessful) {
            _cartProducts.value = Event(response.body()!!)
            _trigger.value = true
        }
    }
}