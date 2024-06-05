package com.test.nopstation_cart.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.ProductListAdapter
import com.test.nopstation_cart.databinding.FragmentProductBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.ProductItem
import com.test.nopstation_cart.models.category.Product
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {

    private val args: ProductFragmentArgs by navArgs()
    private lateinit var categoryName: String
    private lateinit var binding: FragmentProductBinding
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var demoData: ProvideDemoData

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        demoData = ProvideDemoData()
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
        viewModel.itemCount.observe(viewLifecycleOwner) {
            binding.tvCartCount.text = it.toString()
        }
        viewModel.onlineStatus.observe(viewLifecycleOwner){
            if (it == true){
                viewModel.updateItemCount()
            }
        }
    }

    private fun initProduct(){
        //var productList = demoData.getDemoProducts(categoryName)
        binding.rvProductList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProductList.adapter = productListAdapter
        binding.rvProductList.setHasFixedSize(true)
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