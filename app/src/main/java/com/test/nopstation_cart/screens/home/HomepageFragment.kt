package com.test.nopstation_cart.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.BestSellingAdapter
import com.test.nopstation_cart.adapter.OurCategoryAdapter
import com.test.nopstation_cart.databinding.FragmentHomepageBinding
import com.test.nopstation_cart.models.ProductItem
import com.test.nopstation_cart.models.OurCategoryItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomepageFragment : Fragment(R.layout.fragment_homepage) {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var adaptar: BestSellingAdapter
    private lateinit var ourcategoryadaptar: OurCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adaptar = BestSellingAdapter{

        }
        ourcategoryadaptar = OurCategoryAdapter{

        }
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

    }


    fun populateCategory() {
        val categoryList = mutableListOf<OurCategoryItem>()
        categoryList.add(
            OurCategoryItem(
                0,
                "Foods",
                R.drawable.food
            )
        )
        categoryList.add(
            OurCategoryItem(
                1,
                "Watch",
                R.drawable.item_sample
            )
        )
        categoryList.add(
            OurCategoryItem(
                2,
                "Phones",
                R.drawable.phone
            )
        )
        categoryList.add(
            OurCategoryItem(
                3,
                "Furniture",
                R.drawable.chair
            )
        )
        categoryList.add(
            OurCategoryItem(
                4,
                "Fashion",
                R.drawable.fashion
            )
        )

        binding.rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryList.adapter = ourcategoryadaptar
        ourcategoryadaptar.submitList(categoryList)

    }
    fun populateBestSale() {
        val productList = mutableListOf<ProductItem>()

        productList.add(
            ProductItem(
                0,
                "Black Sofa",
                R.drawable.furniture2,
                20.50,
                4.0f
            )
        )
        productList.add(
            ProductItem(
                2,
                "Blue Sofa",
                R.drawable.furniture3,
                25.50,
                3.0f
            )
        )
        productList.add(
            ProductItem(
                3,
                "Brown Sofa",
                R.drawable.furniture1,
                30.50,
                5.0f
            )
        )
        productList.add(
            ProductItem(
                4,
                "California Orange",
                R.drawable.example_item2,
                15.00,
                3.0f
            )
        )

        binding.rvBestSellingProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestSellingProduct.adapter = adaptar
        adaptar.submitList(productList)
    }

    private fun onItemClick(item: ProductItem) {
        // Handle item click here
    }

}