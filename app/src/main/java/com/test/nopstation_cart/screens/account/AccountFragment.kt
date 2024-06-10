package com.test.nopstation_cart.screens.account

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AccountFragment : Fragment(R.layout.fragment_account) {

    private lateinit var binding: FragmentAccountBinding
    private lateinit var sharedPreferences: SharedPreferences
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAccountBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            findNavController().popBackStack(R.id.homepageFragment, false)
        }
    }

}