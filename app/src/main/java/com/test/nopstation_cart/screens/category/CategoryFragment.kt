package com.test.nopstation_cart.screens.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.test.nopstation_cart.R
import com.test.nopstation_cart.adapter.CategoryListAdapter
import com.test.nopstation_cart.databinding.FragmentCategoryBinding
import com.test.nopstation_cart.demodata.ProvideDemoData
import com.test.nopstation_cart.models.OurCategoryItem
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var demoData: ProvideDemoData
    private lateinit var adapter: CategoryListAdapter

    private val viewModel: CartItemCountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CategoryListAdapter{
               // onItemClick(it)
        }
        demoData = ProvideDemoData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCategoryBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        populateCategories()
        viewModel.checkOnlineStatus()
        initObserver()
        binding.ibCheckout.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragmentToCartFragment()
            findNavController().navigate(action)
        }
    }

    private fun populateCategories() {
        val category = demoData.getCategoryList()
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCategory.adapter = adapter
        adapter.submitList(category)
    }

    private fun initObserver(){
        viewModel.itemCount.observe(viewLifecycleOwner){
            binding.tvCartCount.text = it.toString()
        }
        viewModel.onlineStatus.observe(viewLifecycleOwner){
            if(it == true){
                viewModel.updateItemCount()
            }
        }
    }
}