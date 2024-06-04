package com.test.nopstation_cart.screens.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.banner.Slider
import com.test.nopstation_cart.repository.BannerRepository
import com.test.nopstation_cart.repository.FeaturedProductRepository
import com.test.nopstation_cart.utils.InternetStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import com.test.nopstation_cart.models.category.Data as CategoryData
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val repository: BannerRepository,
    private val repository2: FeaturedProductRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _onlineStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val onlineStatus: LiveData<Boolean>
        get() = _onlineStatus

    fun checkOnlineStatus() {
        _onlineStatus.value = InternetStatus.isOnline(context.applicationContext)
    }

    private val _banner: MutableLiveData<List<Slider>> by lazy {
        MutableLiveData<List<Slider>>()
    }
    val banner: LiveData<List<Slider>>
        get() = _banner


    fun getBanner() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
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
        if (InternetStatus.isOnline(context.applicationContext)) {
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

    private val _categories: MutableLiveData<List<CategoryData>> by lazy {
        MutableLiveData<List<CategoryData>>()
    }
    val categories: LiveData<List<CategoryData>>
        get() = _categories

    fun getCategories() = viewModelScope.launch {
        if (InternetStatus.isOnline(context.applicationContext)) {
            val response = repository2.getCategoryWithProducts()
            response.isSuccessful.let {
                if (it) {
                    _categories.value = response.body()?.data
                }
            }
        } else {
            _categories.value = repository2.getCategoriesFromRoom()
        }
    }

}