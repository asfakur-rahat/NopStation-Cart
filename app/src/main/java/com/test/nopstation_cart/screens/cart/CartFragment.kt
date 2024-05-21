package com.test.nopstation_cart.screens.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.CartAdapter
import com.test.nopstation_cart.databinding.FragmentCartBinding
import com.test.nopstation_cart.demodata.ProvideDemoData

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    private lateinit var adapter: CartAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CartAdapter {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCartBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        val data = ProvideDemoData().getCartData()
        binding.cartItems.layoutManager = LinearLayoutManager(requireContext())
        binding.cartItems.adapter = adapter
        adapter.submitList(data)
        binding.totalItems.text = "${data.size} ITEM"+(if(data.size>1) "(S)" else "")

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}