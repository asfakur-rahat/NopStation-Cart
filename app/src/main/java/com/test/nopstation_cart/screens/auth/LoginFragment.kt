package com.test.nopstation_cart.screens.auth

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.nopstation_cart.R
import com.test.nopstation_cart.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        initObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString().trimMargin()
            val password = binding.etPassword.text.toString().trimMargin()
            viewModel.postLogin(email, password)
        }
    }

    private fun initObserver(){
        viewModel.response.observe(this){ loginResponse ->
            sharedPreferences.edit().putString("Token", loginResponse?.data?.token).apply()
            findNavController().popBackStack()
        }
        viewModel.showMessage.observe(this){message ->
            if (message != "")
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}