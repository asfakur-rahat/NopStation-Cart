package com.test.nopstation_cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.test.nopstation_cart.databinding.ActivityMainBinding
import com.test.nopstation_cart.screens.home.HomepageFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("Token", "null").toString()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainNavbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    println("Account")
                    binding.mainNavbar.visibility = View.GONE

                    if (token == "null"){
                        navController.navigate(R.id.accountFragment)
                        return@addOnDestinationChangedListener
                    }

                }
                else -> binding.mainNavbar.visibility = View.VISIBLE
            }
        }

//        val navBarItem = binding.mainNavbar.menu.findItem(R.id.loginFragment)
//
//        println(token)
//
//        navBarItem.setOnMenuItemClickListener {
//            if(token == null) {
//                navController.navigate(R.id.loginFragment)
//                true
//            }
//            else{
//                navController.navigate(R.id.accountFragment)
//                true
//            }
//        }
    }

}