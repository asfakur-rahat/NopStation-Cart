package com.test.nopstation_cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.test.nopstation_cart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainNavbar.setOnItemSelectedListener { navitem ->
            when(navitem.itemId){
                R.id.home -> {
                    true
                }
                R.id.category -> {
                    true
                }
                R.id.search -> {
                    true
                }
                R.id.account -> {
                    true
                }
                R.id.more -> {
                    true
                }
                else -> false
            }
        }
    }
}