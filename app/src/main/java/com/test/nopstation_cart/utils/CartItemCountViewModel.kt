package com.test.nopstation_cart.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartItemCountViewModel @Inject constructor(
    private val repository: CartRepository,
    private val isOnline: Boolean
): ViewModel(){

    private val _onlineStatus : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val onlineStatus : LiveData<Boolean>
        get() = _onlineStatus
    fun checkOnlineStatus() {
        _onlineStatus.value = isOnline
    }


    private val _itemCount : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val itemCount: LiveData<Int>
            get() = _itemCount

    fun updateItemCount() = viewModelScope.launch {
        val response = repository.fetchCartItems()
        if(response.isSuccessful){
            _itemCount.value = response.body()?.data?.cart?.items?.size
        }
    }
}