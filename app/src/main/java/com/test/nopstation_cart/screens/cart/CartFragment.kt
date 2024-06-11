package com.test.nopstation_cart.screens.cart

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
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
    private var item: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = requireContext().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        adapter = CartAdapter(
            onClick = {
                onCancleClick(it)
            },
            onUpdate = { item, newQuantity ->
                onUpdateClick(item,newQuantity)
            },
            onZeroAlert = {
                Toast.makeText(requireContext(), "You can't decrease the quantity of this item anymore", Toast.LENGTH_SHORT).show()
            }
        )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCartBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initShimmer()
        viewModel.checkOnlineStatus()
        initObservers()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.bottomButton.setOnClickListener {
            if(sharedPreferences.getString("Token",null) == null){
                findNavController().navigate(R.id.loginFragment)
            }else {
                if(item <= 0){
                    Toast.makeText(requireContext(), "Cart is empty", Toast.LENGTH_SHORT).show()
                }else{
                    findNavController().navigate(R.id.checkOutFragment)
                }
            }
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
            if(it==true){
                viewModel.fetchCart()
            }
        }
        viewModel.loader.observe(viewLifecycleOwner){
            if(it == true){
                binding.loading.visibility = View.VISIBLE
                binding.nestedScroll.setOnTouchListener { _, _ -> true }

                setChildrenEnabled(binding.nestedScroll,false)

            }else{
                binding.loading.visibility = View.GONE
                binding.nestedScroll.setOnTouchListener { _, _ -> false }
                setChildrenEnabled(binding.nestedScroll,true)
            }
        }
    }

    private fun setChildrenEnabled(viewGroup: ViewGroup, enabled: Boolean) {
        for (i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            child.isEnabled = enabled
            if (child is ViewGroup) {
                setChildrenEnabled(child, enabled)
            }
        }
    }

    private fun initShimmer(){
        binding.second.visibility = View.INVISIBLE
        binding.nestedScroll.visibility = View.INVISIBLE
        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }


    private fun initView(it: FetchCartResponse){
        item = it.data.cart.items.size
        binding.totalItems.text = "${it.data.cart.items.size} ITEM" +(if(it.data.cart.items.size>1) "(S)" else "")
        binding.tvCartCount.text = "${it.data.cart.items.size}"
        binding.tvSubtotalAmount.text = it.data.orderTotals.subTotal ?: "$0.00"
        binding.tvShippingAmount.text = it.data.orderTotals.shipping ?: "$0.00"
        binding.tvTotalAmount.text = it.data.orderTotals.orderTotal ?: "$0.00"
        binding.shimmerLayout.stopShimmer()
        binding.second.visibility = View.VISIBLE
        binding.nestedScroll.visibility = View.VISIBLE
        binding.shimmerLayout.visibility = View.GONE
    }
}