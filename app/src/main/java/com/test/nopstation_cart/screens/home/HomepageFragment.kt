package com.test.nopstation_cart.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.BestSellingAdapter
import com.test.nopstation_cart.adapter.FeaturedProductAdapter
import com.test.nopstation_cart.adapter.FurnitureCollectionAdapter
import com.test.nopstation_cart.adapter.OurCategoryAdapter
import com.test.nopstation_cart.adapter.SalmonAdapter
import com.test.nopstation_cart.adapter.WomenHeelAdaptar
import com.test.nopstation_cart.databinding.FragmentHomepageBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.OurCategoryItem
import com.test.nopstation_cart.models.ProductItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class HomepageFragment : Fragment(R.layout.fragment_homepage) {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var bestsellAdaptar: BestSellingAdapter
    private lateinit var ourcategoryadaptar: OurCategoryAdapter
    private lateinit var featuredAdaptar: FeaturedProductAdapter
    private lateinit var womenHeelAdapter: WomenHeelAdaptar
    private lateinit var salmonAdapter: SalmonAdapter
    private lateinit var furnitureCollectionAdapter: FurnitureCollectionAdapter
    private lateinit var dataProvider: ProvideDemoData

    private val viewModel: HomepageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bestsellAdaptar = BestSellingAdapter{
                onItemClick(it)
        }
        ourcategoryadaptar = OurCategoryAdapter{
                onCategoryClick(it)
        }
        featuredAdaptar = FeaturedProductAdapter{
                onItemClick(it)
        }
        womenHeelAdapter = WomenHeelAdaptar{
                onItemClick(it)
        }
        salmonAdapter = SalmonAdapter{
                onItemClick(it)
        }
        furnitureCollectionAdapter = FurnitureCollectionAdapter{
                onItemClick(it)
        }
        dataProvider = ProvideDemoData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding = FragmentHomepageBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBannerFromApi()
        initObserver()


        binding.ibCheckout.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToCartFragment()
            findNavController().navigate(action)
        }

        populateCategory()
        populateBestSale()
        populateFeaturedProduct()
        populateWomenHeel()
        populateSalmon()
        populateFurnitureCollection()

    }


    private fun initObserver() {
        viewModel.banner.observe(viewLifecycleOwner) { data ->
            binding.carouselBanner.registerLifecycle(viewLifecycleOwner)
            for (item in data.sliders) {
                val carouselItem = CarouselItem(imageUrl = item.imageUrl)
                binding.carouselBanner.addData(carouselItem)
            }
        }
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
        val productList = dataProvider.provideWomenHeel()
        binding.rvWomensHeelProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvWomensHeelProducts.adapter = womenHeelAdapter
        womenHeelAdapter.submitList(productList)
    }

    fun populateSalmon() {
        val productList = dataProvider.provideSalmon()
        binding.rvSalmonFishProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSalmonFishProducts.adapter = salmonAdapter
        salmonAdapter.submitList(productList)
    }
    fun populateFurnitureCollection() {
        val productList = dataProvider.provideFurnitureCollection()
        binding.rvFurnitureCollection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFurnitureCollection.adapter = furnitureCollectionAdapter
        furnitureCollectionAdapter.submitList(productList)
    }

    private fun onItemClick(item: ProductItem) {
        // Handle item click here
        val action = HomepageFragmentDirections.actionHomepageFragmentToProductDetailFragment(productImage = item.productImage, productName =  item.productName)
        findNavController().navigate(action)
    }
    private fun onCategoryClick(item: OurCategoryItem) {
        // Handle item click here
        val action = HomepageFragmentDirections.actionHomepageFragmentToProductFragment(categoryName = item.categoryName, categoryImage =  item.categoryImage)
        findNavController().navigate(action)
    }

}