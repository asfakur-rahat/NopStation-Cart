package com.test.nopstation_cart.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.ProductListAdapter
import com.test.nopstation_cart.databinding.FragmentProductBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.ProductItem

class ProductFragment : Fragment(R.layout.fragment_product) {

    private val args: ProductFragmentArgs by navArgs()
    private lateinit var categoryName: String
    private lateinit var binding: FragmentProductBinding
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var demoData: ProvideDemoData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        demoData = ProvideDemoData()
        categoryName = args.categoryName
        productListAdapter = ProductListAdapter{
                //onItemClick(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductBinding.bind(view)
        binding.navLogo.text = categoryName
        binding.tvCategoryName.text = categoryName
        binding.ivBanner.setImageResource(args.categoryImage)
        super.onViewCreated(view, savedInstanceState)
        initproduct()
        binding.ibCheckout.setOnClickListener {
            goToCart()
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initproduct(){
        var productList = demoData.getDemoProducts(categoryName)
        binding.rvProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductList.adapter = productListAdapter
        binding.rvProductList.setHasFixedSize(true)
        productListAdapter.submitList(productList)
    }

//    private fun onItemClick(item: ProductItem){
//        val action = ProductFragmentDirections.actionProductFragmentToProductDetailFragment(item.productImage,item.productName)
//        findNavController().navigate(action)
//    }

    private fun goToCart(){
        val action = ProductFragmentDirections.actionProductFragmentToCartFragment()
        findNavController().navigate(action)
    }
}