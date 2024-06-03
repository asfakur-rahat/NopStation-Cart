package com.test.nopstation_cart.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.db.AppDatabase
import com.test.nopstation_cart.models.CategoryItem
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.banner.Data
import com.test.nopstation_cart.models.banner.Slider
import com.test.nopstation_cart.models.category.CategoryResponse
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.BannerApi
import com.test.nopstation_cart.network.api.FeaturedProductApi
import com.test.nopstation_cart.repository.BannerRepository
import com.test.nopstation_cart.repository.FeaturedProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val repository: BannerRepository,
    private val repository2: FeaturedProductRepository,
    private val isOnline: Boolean
) : ViewModel() {

    private val _onlineStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val onlineStatus: LiveData<Boolean>
        get() = _onlineStatus

    fun checkOnlineStatus() {
        _onlineStatus.value = isOnline
    }

    private val _banner: MutableLiveData<List<Slider>> by lazy {
        MutableLiveData<List<Slider>>()
    }
    val banner: LiveData<List<Slider>>
        get() = _banner


    fun getBanner() = viewModelScope.launch {
        if (isOnline) {
            repository.getBanner().isSuccessful.let {
                if (it) {
                    _banner.value = repository.getBanner().body()?.data?.sliders
                }
            }
        } else {
            _banner.value = repository.getBannerFromRoom()
        }
    }


    private val _featuredProducts: MutableLiveData<List<ProductItems>> by lazy {
        MutableLiveData<List<ProductItems>>()
    }
    val featuredProducts: LiveData<List<ProductItems>>
        get() = _featuredProducts

    fun getFeaturedProducts() = viewModelScope.launch {
        if (isOnline) {
            val response = repository2.getFeaturedProducts()
            val data = response.body()?.data
            val list = mutableListOf<ProductItems>()
            data?.forEach {
                list.add(
                    ProductItems(
                        id = it.id,
                        productName = it.name,
                        productImage = it.pictureModels[0].imageUrl,
                        productPrice = it.productPrice.priceValue,
                        productRating =
                        if (it.reviewOverviewModel.totalReviews == 0) {
                            0.0f
                        } else {
                            it.reviewOverviewModel.ratingSum.toFloat() / it.reviewOverviewModel.totalReviews.toFloat()
                        }
                    )
                )
            }
            _featuredProducts.value = list
        } else {
            val data = repository2.getFeaturedProductsFromRoom()
            val list = mutableListOf<ProductItems>()
            data.forEach {
                list.add(
                    ProductItems(
                        id = it.id,
                        productName = it.name,
                        productImage = it.pictureModels[0].imageUrl,
                        productPrice = it.productPrice.priceValue,
                        productRating =
                        if (it.reviewOverviewModel.totalReviews == 0) {
                            0.0f
                        } else {
                            it.reviewOverviewModel.ratingSum.toFloat() / it.reviewOverviewModel.totalReviews.toFloat()
                        }
                    )
                )
            }
            _featuredProducts.value = list
        }
    }

    private val _categories: MutableLiveData<List<com.test.nopstation_cart.models.category.Data>> by lazy {
        MutableLiveData<List<com.test.nopstation_cart.models.category.Data>>()
    }
    val categories: LiveData<List<com.test.nopstation_cart.models.category.Data>>
        get() = _categories

    fun getCategories() = viewModelScope.launch {
        if (isOnline) {
            repository2.getCategoryWithProducts().isSuccessful.let {
                if (it) {
                    _categories.value = repository2.getCategoryWithProducts().body()?.data
                }
            }
        } else {
            _categories.value = repository2.getCategoriesFromRoom()
        }
    }

}