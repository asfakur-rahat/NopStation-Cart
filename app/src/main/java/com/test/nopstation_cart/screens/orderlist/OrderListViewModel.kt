package com.test.nopstation_cart.screens.orderlist

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.db.dbmodel.OrderEntity
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val repository: CartRepository,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _orders : MutableLiveData<List<OrderEntity>> by lazy {
        MutableLiveData<List<OrderEntity>>()
    }
    val orders : LiveData<List<OrderEntity>>
        get() = _orders

    fun getOrders() = viewModelScope.launch {
        var email = sharedPreferences.getString("email", null)
        if(email == null) {
            email = ""
        }
        _orders.value = repository.getOrdersFromRoom(email)
    }
    private val _cartItemCount: MutableLiveData<Int> = MutableLiveData()
    val cartItemCount: LiveData<Int>
        get() = _cartItemCount

    fun updateCartItemCount() = viewModelScope.launch(coroutineExceptionHandler) {
        if (InternetStatus.isOnline(context.applicationContext)){
            val response = repository.fetchCartItems()
            if (response.isSuccessful){
                _cartItemCount.value = response.body()?.data?.cart?.items?.size
            }
        }
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
       //TODO handle error
    }
}