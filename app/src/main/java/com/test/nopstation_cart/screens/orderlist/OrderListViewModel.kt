package com.test.nopstation_cart.screens.orderlist

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.db.dbmodel.OrderEntity
import com.test.nopstation_cart.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    private val repository: CartRepository,
    private val sharedPreferences: SharedPreferences
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

    fun updateCartItemCount() = viewModelScope.launch {
        val response = repository.fetchCartItems()
        if (response.isSuccessful){
            _cartItemCount.value = response.body()?.data?.cart?.items?.size
        }
    }
}