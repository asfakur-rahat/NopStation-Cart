package com.test.nopstation_cart.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.CategoryItem
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.banner.Data
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
    private val repository2: FeaturedProductRepository
): ViewModel() {
    private val _banner : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val banner : LiveData<Data>
        get() = _banner

    private val _featuredProducts: MutableLiveData<List<ProductItems>> by lazy {
        MutableLiveData<List<ProductItems>>()
    }
    val featuredProducts: LiveData<List<ProductItems>>
        get() = _featuredProducts

    fun getBannerFromApi() = viewModelScope.launch{
        val response = repository.getBanner()
        if(response.isSuccessful){
            _banner.value = response.body()?.data
        }
    }

    fun getFeaturedProducts() = viewModelScope.launch {
        val response = repository2.getFeaturedProducts()
        if (response.isSuccessful) {
            val data = response.body()?.data
            var list = mutableListOf<ProductItems>()
            for (item in data!!){
                println(item)
                list.add(
                    ProductItems(
                        id = item.reviewOverviewModel.productId,
                        productName = item.name,
                        productImage = item.pictureModels[0].imageUrl,
                        productPrice = item.productPrice.priceValue,
                        productRating =
                            if(item.reviewOverviewModel.totalReviews == 0) {
                                0.0f
                            }
                            else {
                                item.reviewOverviewModel.ratingSum.toFloat() /item.reviewOverviewModel.totalReviews.toFloat()
                            }
                    )
                )
            }
            _featuredProducts.value = list
        }
    }

    private val _categories: MutableLiveData<CategoryResponse> by lazy {
        MutableLiveData<CategoryResponse>()
    }
    val categories: LiveData<CategoryResponse>
        get() = _categories

    fun getCategories() = viewModelScope.launch {
        val response = repository2.getCategoryWithProducts()
        if (response.isSuccessful) {
            _categories.value = response.body()
        }
    }

}