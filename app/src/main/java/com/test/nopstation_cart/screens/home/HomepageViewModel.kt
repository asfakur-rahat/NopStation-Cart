package com.test.nopstation_cart.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.nopstation_cart.models.CategoryItem
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.banner.Data
import com.test.nopstation_cart.network.ApiClient
import com.test.nopstation_cart.network.api.BannerApi
import com.test.nopstation_cart.network.api.FeaturedProductApi
import com.test.nopstation_cart.repository.BannerRepository
import com.test.nopstation_cart.repository.FeaturedProductRepository
import kotlinx.coroutines.launch
class HomepageViewModel: ViewModel() {
    private val _banner : MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val banner : MutableLiveData<Data>
        get() = _banner

    private val _featuredProducts: MutableLiveData<List<ProductItems>> by lazy {
        MutableLiveData<List<ProductItems>>()
    }
    val featuredProducts: MutableLiveData<List<ProductItems>>
        get() = _featuredProducts


    private val apiClient = ApiClient.getClient().create(BannerApi::class.java)
    private val repository = BannerRepository(apiClient)


    fun getBannerFromApi() = viewModelScope.launch{
        val response = repository.getBanner()
        if(response.isSuccessful){
            _banner.value = response.body()?.data
        }
    }

    private val apiClient2 = ApiClient.getClient().create(FeaturedProductApi::class.java)
    private val repository2 = FeaturedProductRepository(apiClient2)

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


    private val _categories: MutableLiveData<List<CategoryItem>> by lazy {
        MutableLiveData<List<CategoryItem>>()
    }
    val categories: MutableLiveData<List<CategoryItem>>
        get() = _categories

    fun getCategories() = viewModelScope.launch {
        val response = repository2.getCategoryWithProducts()
        if (response.isSuccessful) {
            val data = response.body()?.data
            var list = mutableListOf<CategoryItem>()

            for (item in data!!) {
                list.add(
                    CategoryItem(
                        id = item.id,
                        categoryName = item.name,
                        categoryImage = item.products[0].pictureModels[0].imageUrl,
                        categoryList = item.products
                    )
                )
            }
            _categories.value = list
        }
    }

}