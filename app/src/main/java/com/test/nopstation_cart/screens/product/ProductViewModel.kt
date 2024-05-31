package com.test.nopstation_cart.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.nopstation_cart.models.cart.AddToCartRequest
import com.test.nopstation_cart.models.cart.FormValue
import com.test.nopstation_cart.repository.CartRepository
import com.test.nopstation_cart.screens.productdetail.ProductDetailsViewModel
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val viewModel: CartItemCountViewModel,
    private val repository: CartRepository
) : ViewModel() {


    fun addToCart(id: Int) =  viewModelScope.launch {
        repository.AddToCart(id, AddToCartRequest(listOf(
            FormValue(
                key = "addtocart_${id}.EnteredQuantity",
                value = "1"
            )
        ))).isSuccessful.apply {
            updateCartCount()
        }
    }

    private fun updateCartCount() = viewModelScope.launch {
        viewModel.updateItemCount()
    }

}