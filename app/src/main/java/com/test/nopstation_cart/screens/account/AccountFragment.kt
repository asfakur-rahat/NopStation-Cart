package com.test.nopstation_cart.screens.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val viewModel: AccountViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("Token", null)
        if (token == null) {
            Toast.makeText(requireContext(), "You have to Logged in first", Toast.LENGTH_SHORT).show()
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.userinfo.observe(this){
            binding.tvLoggedInUser.text = buildString {
                append(it.data.firstName)
                append(" ")
                append(it.data.lastName)
            }
            binding.tvEmail.text = it.data.email
        }
        viewModel.showMassage.observe(this){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        viewModel.getUserInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAccountBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            findNavController().popBackStack(R.id.homepageFragment, false)
        }
    }

}