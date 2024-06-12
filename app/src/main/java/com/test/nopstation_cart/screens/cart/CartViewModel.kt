package com.test.nopstation_cart.screens.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.FetchCartResponse
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.models.cart.Item
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _onlineStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val onlineStatus: LiveData<Boolean>
        get() = _onlineStatus

    fun checkOnlineStatus() {
        _onlineStatus.value = InternetStatus.isOnline(context.applicationContext)
    }

    private val _cartList: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val cartList: LiveData<FetchCartResponse>
        get() = _cartList


    private val _loader: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val loader: LiveData<Boolean>
        get() = _loader

    fun fetchCart() = viewModelScope.launch(coroutineExceptionHandler) {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.fetchCartItems()
            if (response.isSuccessful) {
                _cartList.value = response.body()
            }
        }
    }

    private val _isCancle: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val isCancle: LiveData<FetchCartResponse>
        get() = _isCancle

    fun removeItem(item: Item) = viewModelScope.launch(coroutineExceptionHandler) {
        if (InternetStatus.isOnline(context.applicationContext)) {
            _loader.value = true
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
                _loader.value = false
                _isCancle.value = response.body()
            }
        }
    }

    private val _quantityUpdated: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val quantityUpdated: LiveData<FetchCartResponse>
        get() = _quantityUpdated

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage: LiveData<String>
        get() = _showMessage

    fun updateQuantity(item: Item, quantity: Int) = viewModelScope.launch(coroutineExceptionHandler) {
        if (InternetStatus.isOnline(context.applicationContext)) {
            _loader.value = true
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
            if (response.isSuccessful) {
                _loader.value = false
                _showMessage.value = "Quantity Updated"
                _quantityUpdated.value = response.body()
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _loader.value = false
        _showMessage.value = "Check your internet connection!!"
    }

}