package com.test.nopstation_cart.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.ProductListAdapter
import com.test.nopstation_cart.databinding.FragmentProductBinding
import com.test.nopstation_cart.models.category.Product
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {

    private val args: ProductFragmentArgs by navArgs()
    private lateinit var categoryName: String
    private lateinit var binding: FragmentProductBinding
    private lateinit var productListAdapter: ProductListAdapter

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryName = args.CategoryName
        productListAdapter = ProductListAdapter(
            {
                onItemClick(it)
            },
            {
                viewModel.addToCart(it.id)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductBinding.bind(view)
        binding.navLogo.text = categoryName
        binding.tvCategoryName.text = categoryName
        binding.ivBanner.load(args.Product[0].pictureModels[0].imageUrl)
        super.onViewCreated(view, savedInstanceState)
        initProduct()
        viewModel.checkOnlineStatus()
        initObserver()
        binding.ibCheckout.setOnClickListener {
            goToCart()
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObserver() {
        viewModel.itemCount.observe(viewLifecycleOwner){
            binding.tvCartCount.text = it.toString()
        }
        viewModel.onlineStatus.observe(viewLifecycleOwner){
            if (it == true){
                viewModel.updateItemCount()
            }
        }
        viewModel.showMessage.observe(viewLifecycleOwner){
            val item = it.getContentIfNotHandled()
            item?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initProduct(){
        binding.rvProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductList.adapter = productListAdapter
        productListAdapter.submitList(args.Product.toMutableList())
    }

    private fun onItemClick(item: Product){
        val action = ProductFragmentDirections.actionProductFragmentToProductDetailFragment(item.id)
        findNavController().navigate(action)
    }

    private fun goToCart(){
        val action = ProductFragmentDirections.actionProductFragmentToCartFragment()
        findNavController().navigate(action)
    }
}