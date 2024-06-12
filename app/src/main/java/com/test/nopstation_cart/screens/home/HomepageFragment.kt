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
import com.test.nopstation_cart.models.ProductItems
import com.test.nopstation_cart.models.category.Data
import com.test.nopstation_cart.screens.productdetail.ProductDetailsViewModel
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


@AndroidEntryPoint
class HomepageFragment : Fragment() {

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var ourcategoryadaptar: OurCategoryAdapter
    private lateinit var featuredAdaptar: FeaturedProductAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel: HomepageViewModel by viewModels()
    private val viewModel2: ProductDetailsViewModel by viewModels ()
    private val cartItemViewModel: CartItemCountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        ourcategoryadaptar = OurCategoryAdapter{ data, name ->
            onCategoryClick(data, name)
        }
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
        featuredAdaptar = FeaturedProductAdapter(
            onClick = { onItemClick(it) },
            onAddToCart = { addToCart(it) }
        )
        initObservers()
        initView()
        binding.ibCheckout.setOnClickListener {
            val action = HomepageFragmentDirections.actionHomepageFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun initView(){
        binding.rvFeaturedProducts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeaturedProducts.adapter = featuredAdaptar
        binding.rvCategoryList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategoryList.adapter = ourcategoryadaptar
        viewModel.getBanner()
        viewModel.getCategories()
        viewModel.getFeaturedProducts()
    }

    private fun initObservers() {
        viewModel.onlineStatus.observe(viewLifecycleOwner){status->
            if(status == true){
                cartItemViewModel.updateItemCount()
            }
        }
        viewModel2.showMessage.observe(viewLifecycleOwner){
            val item = it.getContentIfNotHandled()
            item?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.banner.observe(viewLifecycleOwner) { data ->
            binding.carouselBanner.registerLifecycle(viewLifecycleOwner)
            val list = data.map { CarouselItem(imageUrl = it.imageUrl) }
            binding.carouselBanner.setData(list)
        }

        viewModel.featuredProducts.observe(viewLifecycleOwner) {
            featuredAdaptar.submitList(it)
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            ourcategoryadaptar.submitList(it)
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
