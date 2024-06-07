package com.test.nopstation_cart.screens.checkout

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.db.AppDatabase
import com.test.nopstation_cart.db.dbmodel.OrderEntity
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.FetchCartResponse
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.network.api.CheckoutApi
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.utils.Constants
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class CheckOutViewModel @Inject constructor(
    private val repository: CartRepository,
    @ApplicationContext private val context: Context,
    private val db: AppDatabase
) : ViewModel() {
    private val _cart: MutableLiveData<FetchCartResponse> by lazy {
        MutableLiveData<FetchCartResponse>()
    }
    val cart: LiveData<FetchCartResponse> get() = _cart

    fun fetchCart() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.fetchCartItems()
            if (response.isSuccessful) {
                _cart.value = response.body()
            }
        }
    }

    private val _showMessage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val showMessage: LiveData<String> get() = _showMessage

    fun isValid(
        firstName: String,
        lastName: String,
        email: String,
        company: String,
        country: String,
        state: String,
        zip: String,
        city: String,
        phone: String,
        fax: String
    ) = viewModelScope.launch {
        if (
            firstName.isEmpty() ||
            lastName.isEmpty() ||
            email.isEmpty() ||
            company.isEmpty() ||
            country.isEmpty() ||
            state.isEmpty() ||
            zip.isEmpty() ||
            city.isEmpty() ||
            phone.isEmpty() ||
            fax.isEmpty()
        ) {
            _showMessage.value = "Please fill all fields"
        } else {
            placeOrder()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.CHECKOUT_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getApi(): CheckoutApi {
        return getRetrofit().create(CheckoutApi::class.java)
    }

    private val _orderPlaced: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val orderPlaced: LiveData<Boolean> get() = _orderPlaced
    private var orderId: String? = null
    private fun placeOrder() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = getApi().placeOrder()
            if (response.isSuccessful) {
                _orderPlaced.value = true
                _showMessage.value =
                    response.body()?.message + ", OrderID: " + response.body()?.orderId
                orderId = response.body()?.orderId
            }
        } else {
            _showMessage.value = "No internet connection"
        }
    }

    private val _navigateBack: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val navigateBack: LiveData<Boolean> get() = _navigateBack

    suspend fun removeAllOrders() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository.fetchCartItems()
            if (response.isSuccessful) {
                val orderItems = response.body()?.data?.cart?.items
                if (orderItems != null) {
                    var count = 0
                    val size = orderItems.size
                    for (item in orderItems) {
                        val response2 = repository.updateCart(
                            AddToCartRequest(
                                listOf(
                                    FormValue(
                                        key = "removefromcart",
                                        value = item.id.toString()
                                    )
                                )
                            )
                        )
                        if (response2.isSuccessful) {
                            count++
                        }
                    }
                    if (count == size){
                       savePlacedOrderInRoom(
                           OrderEntity(
                               id = 1,
                               orderId = orderId!!,
                               item = orderItems
                           )
                       )
                    }
                }
            }
        } else {
            _showMessage.value = "No internet connection"
        }
    }

    private fun savePlacedOrderInRoom(order: OrderEntity) = viewModelScope.launch {
        db.orderDao().saveOrder(order)
        _navigateBack.value = true
    }


}