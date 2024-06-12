package com.test.nopstation_cart.screens.account

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.db.dbmodel.OrderEntity
import com.test.nopstation_cart.models.account.UserAccountResponse
import com.test.nopstation_cart.repository.AccountRepository
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AccountRepository,
    private val sharedPreferences: SharedPreferences,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _userinfo: MutableLiveData<UserAccountResponse> by lazy {
        MutableLiveData<UserAccountResponse>()
    }
    val userinfo: LiveData<UserAccountResponse>
        get() = _userinfo

    private val _showMassage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMassage: LiveData<String>
        get() = _showMassage

    private val _orders: MutableLiveData<List<OrderEntity>> by lazy {
        MutableLiveData<List<OrderEntity>>()
    }
    val orders: LiveData<List<OrderEntity>>
        get() = _orders
    fun getUserInfo() = viewModelScope.launch(coroutineExceptionHandler){
        if(InternetStatus.isOnline(context.applicationContext)){
            val response = repository.getProfile()
            if (response.isSuccessful){
                _userinfo.value = response.body()
                orderCount()
            }
        }else{
            _showMassage.value = "No Internet Connection"
        }
    }
    private fun orderCount() = viewModelScope.launch(coroutineExceptionHandler){
        val email = sharedPreferences.getString("email",null)
        val response = cartRepository.getOrdersFromRoom(email?:"")
        _orders.value = response
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _showMassage.value = "Check your internet connection!!"
    }

}