package com.test.nopstation_cart.screens.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.test.nopstation_cart.models.Auth.Data
import com.test.nopstation_cart.models.Auth.DataX
import com.test.nopstation_cart.models.Auth.Login
import com.test.nopstation_cart.models.Auth.LoginResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.AuthenticationApi
import com.test.nopstation_cart.repository.LoginRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val sharedPreferences: SharedPreferences,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _response: MutableLiveData<LoginResponse> by lazy {
        MutableLiveData<LoginResponse>()
    }
    val response: LiveData<LoginResponse>
        get() = _response

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val showMessage: LiveData<String>
        get() = _showMessage

    private fun isValid(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) {
            _showMessage.value = "Please fill all fields"
            return false
        }
        return true
    }

    fun postLogin(email: String, password: String) = viewModelScope.launch(coroutineExceptionHandler) {
        if (!isValid(email, password)) {
            return@launch
        }
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.userLogin(
                Login(
                    data = DataX(
                        email = email,
                        password = password
                    )
                )
            )
            if (response.isSuccessful) {
                sharedPreferences.edit().putString("email",email).apply()
                _showMessage.value = "Login Successful"
                _response.value = response.body()
            }else{
                if(response.code() == 400){
                    val errorResponse = response.errorBody()?.string()?.let{
                        Gson().fromJson(it, LoginResponse::class.java)
                    }
                    println(errorResponse)
                    _showMessage.value = errorResponse?.errorList?.get(0)?.toString()
                }else
                    _showMessage.value = "Something went wrong"
            }
        }
    }
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _showMessage.value = "Check your internet connection!!"
    }
}