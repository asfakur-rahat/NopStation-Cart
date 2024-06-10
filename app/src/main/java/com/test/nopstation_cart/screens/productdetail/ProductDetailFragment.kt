package com.test.nopstation_cart.screens.productdetail

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentProductDetailBinding
import com.test.nopstation_cart.models.product_details.Data
import com.test.nopstation_cart.utils.CartItemCountViewModel
import com.test.nopstation_cart.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: ProductDetailsViewModel by viewModels()
    private val cartCountViewModel: CartItemCountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentProductDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        initShimmer()
        viewModel.getProductDetails(args.productID)
        cartCountViewModel.updateItemCount()
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
                binding.tvQuantity.text =
                    (binding.tvQuantity.text.toString().toInt() - 1).toString()
            }
        }
        binding.tvQuantityPlus.setOnClickListener {
            binding.tvQuantity.text = (binding.tvQuantity.text.toString().toInt() + 1).toString()
        }
        binding.tvAddToCart.setOnClickListener {
            viewModel.addToCart(
                productID = args.productID,
                quantity = binding.tvQuantity.text.toString().toInt()
            )
        }
    }

    private fun initShimmer() {
        binding.nestedScrollView.visibility = View.INVISIBLE
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }


    private fun initObserver() {
        viewModel.showMessage.observe(viewLifecycleOwner) {
            val item = it.getContentIfNotHandled()
            item?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.product.observe(viewLifecycleOwner) {
            initProductDetail(it)
        }
        viewModel.cartProducts.observe(viewLifecycleOwner) {
            val item = it.getContentIfNotHandled()
            item?.message?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.trigger.observe(viewLifecycleOwner) {
            if (it) {
                cartCountViewModel.updateItemCount()
            }
        }
        cartCountViewModel.itemCount.observe(viewLifecycleOwner) {
            binding.tvCartCount.text = it.toString()
        }
    }

    private fun initProductDetail(it: Data) {
        var sub = it.shortDescription
        var des = it.fullDescription

        if (it.shortDescription.isNullOrEmpty()) {
            sub = Constants.subtitle
        }
        if (it.fullDescription.isNullOrEmpty()) {
            des = Constants.title
        }
        binding.ivProductImage.load(it.pictureModels[0].imageUrl)
        binding.navLogo.text = it.name
        binding.tvProductName.text = it.name
        binding.tvProductSubtitle.text = Html.fromHtml(sub, Html.FROM_HTML_MODE_LEGACY)
        binding.tvProductDiscountedPrice.text =
            "$%.2f".format(it.productPrice.basePricePAngVValue)
        binding.tvProductActualPrice.text = it.productPrice.price
        binding.tvProductActualPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.tvStockStatus.text = it.stockAvailability
        binding.tvQuantity.text = "1"
        binding.tvProductDescription.text = Html.fromHtml(des, Html.FROM_HTML_MODE_LEGACY)
        binding.shimmerLayout.stopShimmer()
        binding.nestedScrollView.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
    }
}