package com.test.nopstation_cart.screens.productdetail

import android.content.Context
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
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductDetailsRepository,
    private val cartRepository: CartRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _showMessage: MutableLiveData<Event<String>> by lazy {
        MutableLiveData<Event<String>>()
    }
    val showMessage: LiveData<Event<String>>
        get() = _showMessage

    private val _product: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val product: LiveData<Data>
        get() = _product

    fun getProductDetails(productID: Int) = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.getProductDetails(productID)
            if (response.isSuccessful) {
                _product.value = response.body()?.data
            }
        } else {
            _showMessage.value = Event<String>("No Internet Connection")
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
        if (InternetStatus.isOnline(context.applicationContext)) {
            val request = AddToCartRequest(
                listOf(
                    FormValue(
                        key = "addtocart_${productID}.EnteredQuantity",
                        value = "$quantity"
                    )
                )
            )
            val response = cartRepository.addToCart(productID, request)
            if (response.isSuccessful) {
                _cartProducts.value = Event(response.body()!!)
                _trigger.value = true
            } else {
                if (response.code() == 400) {
                    _showMessage.value = Event("This product can't be added to cart")
                } else {
                    _showMessage.value = Event("Something went wrong")
                }
            }
        } else {
            _showMessage.value = Event("No Internet Connection")
        }
    }
}