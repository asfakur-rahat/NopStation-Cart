package com.test.nopstation_cart.screens.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
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
import com.test.nopstation_cart.screens.productdetail.ProductDetailsViewModel
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Inject


@AndroidEntryPoint
class HomepageFragment : Fragment() {

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
    private val viewModel2: ProductDetailsViewModel by viewModels ()

    @Inject
    lateinit var cartItemViewModel: CartItemCountViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomepageBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkOnlineStatus()
        initObservers()
        populateBestSale()
        populateWomenHeel()
        populateSalmon()
        populateFurnitureCollection()

        binding.ibCheckout.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun initView(){
        featuredAdaptar = FeaturedProductAdapter(
            onClick = { onItemClick(it) },
            onAddToCart = { addToCart(it) }
        )
        cartItemViewModel.updateItemCount()
        viewModel.getBannerFromApi()
        viewModel.getCategories()
        viewModel.getFeaturedProducts()



        binding.rvFeaturedProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedProducts.adapter = featuredAdaptar
        binding.rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryList.adapter = ourcategoryadaptar
    }

    private fun initObservers() {
        viewModel.onlineStatus.observe(viewLifecycleOwner){
            if(it == false){
                return@observe
            }
            else{
                initView()
            }
        }


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
            item?.message?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel2.trigger.observe(viewLifecycleOwner){
            if(it){
                cartItemViewModel.updateItemCount()
            }
        }
        cartItemViewModel.itemCount.observe(viewLifecycleOwner){
            binding.tvCartCount.text = it.toString()
        }
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
