package com.test.nopstation_cart.screens.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.nopstation_cart.repository.PreferenceRepository


class ProductDetailsViewModelFactory(private val preferenceRepository: PreferenceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductDetailsViewModel(preferenceRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
