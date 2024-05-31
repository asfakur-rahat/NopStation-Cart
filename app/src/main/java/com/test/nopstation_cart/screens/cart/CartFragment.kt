package com.test.nopstation_cart.screens.cart

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
import com.test.nopstation_cart.adapter.CartAdapter
import com.test.nopstation_cart.databinding.FragmentCartBinding
import com.test.nopstation_cart.models.cart.FetchCartResponse
import com.test.nopstation_cart.models.cart.Item
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: CartViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        adapter = CartAdapter(
            onClick = {
                onCancleClick(it)
            },
            onUpdate = { item, newQuantity ->
                onUpdateClick(item,newQuantity)
            }
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCartBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        binding.shimmerLayout.startShimmer()
        initObservers()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onCancleClick(item: Item){
        viewModel.removeItem(item)
    }

    private fun onUpdateClick(item: Item, newQuantity: Int){
        viewModel.updateQuantity(item,newQuantity)
    }


    private fun initObservers(){
        binding.navLogo.text = getString(R.string.shopping_cart_2)
        binding.cartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.cartItems.adapter = adapter

        viewModel.cartList.observe(viewLifecycleOwner){
            adapter.submitList(it.data.cart.items.toMutableList())
            initView(it)
        }
        viewModel.isCancle.observe(viewLifecycleOwner){
            adapter.submitList(it.data.cart.items.toMutableList())
            initView(it)
        }
        viewModel.quantityUpdated.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "Quantity updated", Toast.LENGTH_SHORT).show()
            initView(it)
        }
        viewModel.onlineStatus.observe(viewLifecycleOwner){
            if(it){
                viewModel.fetchCart()
            }
        }
    }

    private fun initView(it: FetchCartResponse){
        binding.totalItems.text = "${it.data.cart.items.size} ITEM" +(if(it.data.cart.items.size>1) "(S)" else "")
        binding.tvCartCount.text = "${it.data.cart.items.size}"
        binding.tvSubtotalAmount.text = it.data.orderTotals.subTotal
        binding.tvShippingAmount.text = it.data.orderTotals.shipping
        binding.tvTotalAmount.text = it.data.orderTotals.orderTotal
        binding.shimmerLayout.stopShimmer()
        binding.second.visibility = View.VISIBLE
        binding.nestedScroll.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
    }
}