package com.test.nopstation_cart.screens.productdetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentProductDetailBinding
import com.test.nopstation_cart.repository.PreferenceRepository
import com.test.nopstation_cart.screens.product.ProductFragmentArgs
import com.test.nopstation_cart.utils.Constants

class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: ProductDetailsViewModel by viewModels {
        ProductDetailsViewModelFactory(PreferenceRepository(sharedPreferences))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.nestedScrollView.visibility = View.INVISIBLE
        binding.shimmerLayout.startShimmer()

        viewModel.getProductDetails(args.productID)
        initObserver()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.ibCheckout.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
            findNavController().navigate(action)
        }
        binding.tvQuantityMinus.setOnClickListener {
            if (binding.tvQuantity.text.toString().toInt() > 1) {
                binding.tvQuantity.text = (binding.tvQuantity.text.toString().toInt() - 1).toString()
            }
        }
        binding.tvQuantityPlus.setOnClickListener {
            binding.tvQuantity.text = (binding.tvQuantity.text.toString().toInt() + 1).toString()
        }
        binding.tvAddToCart.setOnClickListener {
            viewModel.addToCart(productID = args.productID, quantity = binding.tvQuantity.text.toString().toInt())
        }
    }


    //@SuppressLint("ResourceAsColor")
    private fun initObserver() {
        viewModel.product.observe(viewLifecycleOwner){
            println(it)
            var sub = it.shortDescription
            var des = it.fullDescription

            if(it.shortDescription.isNullOrEmpty()){
                sub = Constants.subtitle
            }
            if(it.fullDescription.isNullOrEmpty()){
                des = Constants.title
            }

            binding.ivProductImage.load(it.pictureModels[0].imageUrl)
            binding.navLogo.text = it.name
            binding.tvProductName.text = it.name
            binding.tvProductSubtitle.text = Html.fromHtml(sub, Html.FROM_HTML_MODE_LEGACY)
            binding.tvProductDiscountedPrice.text = "$%.2f".format(it.productPrice.basePricePAngVValue)
            binding.tvProductActualPrice.text = it.productPrice.price
            binding.tvProductActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.tvStockStatus.text = it.stockAvailability
            binding.tvQuantity.text = "1"
            binding.tvProductDescription.text = Html.fromHtml(des, Html.FROM_HTML_MODE_LEGACY)
            binding.shimmerLayout.stopShimmer()
            binding.nestedScrollView.visibility = View.VISIBLE
            binding.shimmerLayout.visibility = View.GONE
        }
        viewModel.cartProducts.observe(viewLifecycleOwner){
            val item = it.getContentIfNotHandled()
            Toast.makeText(requireContext(), item?.message , Toast.LENGTH_SHORT).show()
        }
    }
}