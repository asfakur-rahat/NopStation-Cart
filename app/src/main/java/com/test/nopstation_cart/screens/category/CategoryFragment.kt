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
import com.test.nopstation_cart.screens.home.HomepageFragmentDirections
import com.test.nopstation_cart.models.category.Data as CategoryData
import com.test.nopstation_cart.screens.home.HomepageViewModel
import com.test.nopstation_cart.utils.CartItemCountViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var binding : FragmentCategoryBinding
    private lateinit var demoData: ProvideDemoData
    private lateinit var adapter: CategoryListAdapter

    private val viewModel: CartItemCountViewModel by viewModels()
    private val homepageViewModel: HomepageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = CategoryListAdapter{data, name ->
               onItemClick(data, name)
        }
        demoData = ProvideDemoData()
    }

    private fun onItemClick(data: CategoryData, name: String) {
        val categoryList = data.products.toTypedArray()
        val action = CategoryFragmentDirections.actionCategoryFragmentToProductFragment(categoryList,name)
        findNavController().navigate(action)
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
        homepageViewModel.getCategories()
        binding.rvCategory.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvCategory.adapter = adapter
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
        homepageViewModel.categories.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}