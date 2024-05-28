package com.test.nopstation_cart.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.nopstation_cart.repository.PreferenceRepository

class CartViewModelFactory(private val preferenceRepository: PreferenceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(preferenceRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}