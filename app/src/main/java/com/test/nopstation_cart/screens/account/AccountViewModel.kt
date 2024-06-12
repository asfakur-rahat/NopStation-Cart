package com.test.nopstation_cart.screens.account

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.account.UserAccountResponse
import com.test.nopstation_cart.repository.AccountRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AccountRepository
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

    fun getUserInfo() = viewModelScope.launch(coroutineExceptionHandler){
        if(InternetStatus.isOnline(context.applicationContext)){
            val response = repository.getProfile()
            if (response.isSuccessful){
                _userinfo.value = response.body()
            }
        }else{
            _showMassage.value = "No Internet Connection"
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _showMassage.value = "Check your internet connection!!"
    }

}