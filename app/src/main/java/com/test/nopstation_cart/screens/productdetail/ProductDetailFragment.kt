package com.test.nopstation_cart.screens.productdetail

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentProductDetailBinding
import com.test.nopstation_cart.screens.product.ProductFragmentArgs

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductDetailBinding.bind(view)
        binding.tvProductName.text = args.productName
        binding.navLogo.text = args.productName
        binding.ivProductImage.setImageResource(args.productImage)
        super.onViewCreated(view, savedInstanceState)
        binding.tvProductActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.ibCheckout.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }
}