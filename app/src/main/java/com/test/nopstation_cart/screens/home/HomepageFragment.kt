package com.test.nopstation_cart.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.BestSellingAdapter
import com.test.nopstation_cart.adapter.FeaturedProductAdapter
import com.test.nopstation_cart.adapter.FurnitureCollectionAdapter
import com.test.nopstation_cart.adapter.OurCategoryAdapter
import com.test.nopstation_cart.databinding.FragmentHomepageBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.ProductItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomepageFragment : Fragment(R.layout.fragment_homepage) {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var bestsellAdaptar: BestSellingAdapter
    private lateinit var ourcategoryadaptar: OurCategoryAdapter
    private lateinit var featuredAdaptar: FeaturedProductAdapter
    private lateinit var furnitureCollectionAdapter: FurnitureCollectionAdapter
    private lateinit var dataProvider: ProvideDemoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bestsellAdaptar = BestSellingAdapter{

        }
        ourcategoryadaptar = OurCategoryAdapter{

        }
        featuredAdaptar = FeaturedProductAdapter{

        }
        furnitureCollectionAdapter = FurnitureCollectionAdapter{

        }
        dataProvider = ProvideDemoData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomepageBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.carouselBanner.registerLifecycle(viewLifecycleOwner)

        val bannerProduct = mutableListOf<CarouselItem>()

        bannerProduct.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture1
            )
        )
        bannerProduct.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture2
            )
        )
        bannerProduct.add(
            CarouselItem(
                imageDrawable = R.drawable.furniture3
            )
        )
        binding.carouselBanner.setData(bannerProduct)

        populateCategory()
        populateBestSale()
        populateFeaturedProduct()
        populateWomenHeel()
        populateFurnitureCollection()

    }


    fun populateCategory() {
        val categoryList = dataProvider.provideCategoryitems()
        binding.rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryList.adapter = ourcategoryadaptar
        ourcategoryadaptar.submitList(categoryList)

    }
    fun populateBestSale() {
        val productList = dataProvider.provideBestSellingItems()
        binding.rvBestSellingProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestSellingProduct.adapter = bestsellAdaptar
        bestsellAdaptar.submitList(productList)
    }
    fun populateFeaturedProduct() {
        val productList = dataProvider.provideFeaturedItems()
        binding.rvFeaturedProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedProducts.adapter = featuredAdaptar
        featuredAdaptar.submitList(productList)
    }

    fun populateWomenHeel() {

    }

    fun populateFurnitureCollection() {
        val productList = dataProvider.provideFurnitureCollection()
        binding.rvFurnitureCollection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFurnitureCollection.adapter = furnitureCollectionAdapter
        furnitureCollectionAdapter.submitList(productList)
    }

    private fun onItemClick(item: ProductItem) {
        // Handle item click here
    }

}