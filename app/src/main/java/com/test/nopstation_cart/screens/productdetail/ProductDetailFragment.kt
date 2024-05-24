package com.test.nopstation_cart.screens.productdetail

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentProductDetailBinding
import com.test.nopstation_cart.screens.product.ProductFragmentArgs

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductDetails(args.productID)
        initObserver()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.ibCheckout.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }


    //@SuppressLint("ResourceAsColor")
    private fun initObserver() {
        viewModel.product.observe(viewLifecycleOwner){
            binding.ivProductImage.load(it.pictureModels[0].imageUrl)
            binding.tvProductName.text = it.name
            binding.tvProductSubtitle.text = it.shortDescription
            binding.tvProductDiscountedPrice.text = "$%.2f".format(it.productPrice.basePricePAngVValue)
            binding.tvProductActualPrice.text = it.productPrice.price
            binding.tvProductActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvProductStockStatus.text = it.stockAvailability
            binding.tvQuantity.text = "1"
            binding.tvProductDescription.text = it.metaDescription
        }
    }
}