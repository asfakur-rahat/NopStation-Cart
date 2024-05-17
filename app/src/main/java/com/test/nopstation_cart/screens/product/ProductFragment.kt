package com.test.nopstation_cart.screens.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.test.nopstation_cart.R

class ProductFragment : Fragment(R.layout.fragment_product) {

    //private val args: ProductFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val product = args.categoryId
        //view.findViewById<TextView>(R.id.tv_hello).text = "Hello Category ID $product"
    }
}