package com.test.nopstation_cart.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.*
import com.test.nopstation_cart.databinding.FragmentHomepageBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.category.Data
import com.test.nopstation_cart.repository.PreferenceRepository
import com.test.nopstation_cart.screens.productdetail.ProductDetailsViewModel
import com.test.nopstation_cart.screens.productdetail.ProductDetailsViewModelFactory
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
    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel: HomepageViewModel by viewModels()
    private val viewModel2: ProductDetailsViewModel by viewModels {
        ProductDetailsViewModelFactory(PreferenceRepository(sharedPreferences))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        bestsellAdaptar = BestSellingAdapter{}
        ourcategoryadaptar = OurCategoryAdapter{ data, name ->
            onCategoryClick(data, name)
        }
        womenHeelAdapter = WomenHeelAdaptar{}
        salmonAdapter = SalmonAdapter{}
        furnitureCollectionAdapter = FurnitureCollectionAdapter{}
        dataProvider = ProvideDemoData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomepageBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        featuredAdaptar = FeaturedProductAdapter(
            onClick = { onItemClick(it) },
            onAddToCart = { addToCart(it) }
        )

        initObservers()
        viewModel.getBannerFromApi()
        viewModel.getCategories()
        viewModel.getFeaturedProducts()

        binding.ibCheckout.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToCartFragment()
            findNavController().navigate(action)
        }

        populateBestSale()
        populateWomenHeel()
        populateSalmon()
        populateFurnitureCollection()
    }

    private fun initObservers() {
        viewModel.banner.observe(viewLifecycleOwner) { data ->
            binding.carouselBanner.registerLifecycle(viewLifecycleOwner)
            val list = data.sliders.map { CarouselItem(imageUrl = it.imageUrl) }
            binding.carouselBanner.setData(list)
        }

        viewModel.featuredProducts.observe(viewLifecycleOwner) {
            featuredAdaptar.submitList(it)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            ourcategoryadaptar.submitList(it.data)
        }

        viewModel2.cartProducts.observe(viewLifecycleOwner) {
            val item = it.getContentIfNotHandled()
            if(item?.message != null){
                Toast.makeText(requireContext(), item.message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvFeaturedProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedProducts.adapter = featuredAdaptar

        binding.rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryList.adapter = ourcategoryadaptar
    }

    private fun addToCart(item: ProductItems) {
        viewModel2.addToCart(item.id)
    }

    private fun populateBestSale() {
        val productList = dataProvider.provideBestSellingItems()
        binding.rvBestSellingProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBestSellingProduct.adapter = bestsellAdaptar
        bestsellAdaptar.submitList(productList)
    }

    private fun populateWomenHeel() {
        val productList = dataProvider.provideWomenHeel()
        binding.rvWomensHeelProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvWomensHeelProducts.adapter = womenHeelAdapter
        womenHeelAdapter.submitList(productList)
    }

    private fun populateSalmon() {
        val productList = dataProvider.provideSalmon()
        binding.rvSalmonFishProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSalmonFishProducts.adapter = salmonAdapter
        salmonAdapter.submitList(productList)
    }

    private fun populateFurnitureCollection() {
        val productList = dataProvider.provideFurnitureCollection()
        binding.rvFurnitureCollection.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFurnitureCollection.adapter = furnitureCollectionAdapter
        furnitureCollectionAdapter.submitList(productList)
    }

    private fun onItemClick(item: ProductItems) {
        val action = HomepageFragmentDirections.actionHomepageFragmentToProductDetailFragment(item.id)
        findNavController().navigate(action)
    }

    private fun onCategoryClick(data: Data, name: String) {
        val categoryList = data.products.toTypedArray()
        val action = HomepageFragmentDirections.actionHomepageFragmentToProductFragment(categoryList, name)
        findNavController().navigate(action)
    }
}
