package com.test.nopstation_cart.screens.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
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

    fun postLogin(email: String, password: String) = viewModelScope.launch {

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
                _response.value = response.body()
            }
        }
    }
}