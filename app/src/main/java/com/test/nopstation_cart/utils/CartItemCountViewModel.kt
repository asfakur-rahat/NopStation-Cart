package com.test.nopstation_cart.utils

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartItemCountViewModel @Inject constructor(
    private val repository: CartRepository,
    @ApplicationContext private val context: Context
): ViewModel(){

    private val _onlineStatus : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val onlineStatus : LiveData<Boolean>
        get() = _onlineStatus
    fun checkOnlineStatus() {
        _onlineStatus.value = InternetStatus.isOnline(context.applicationContext)
    }


    private val _itemCount : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val itemCount: LiveData<Int>
            get() = _itemCount

    fun updateItemCount() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.fetchCartItems()
            if (response.isSuccessful) {
                _itemCount.value = response.body()?.data?.cart?.items?.size
            }
        }
    }
}