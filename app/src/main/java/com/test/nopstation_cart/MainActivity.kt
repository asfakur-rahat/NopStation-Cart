package com.test.nopstation_cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.test.nopstation_cart.databinding.ActivityMainBinding
import com.test.nopstation_cart.screens.home.HomepageFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener{
                val zoomOut = AnimationUtils.loadAnimation(this@MainActivity, R.anim.zoom_out)
                it.view.startAnimation(zoomOut)
                zoomOut.setAnimationListener(object: Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                        //
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        it.remove()
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainNavbar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.mainNavbar.visibility = View.GONE
                }
                R.id.productFragment -> {
                    binding.mainNavbar.visibility = View.GONE
                }
                R.id.productDetailFragment -> {
                    binding.mainNavbar.visibility = View.GONE
                }
                R.id.cartFragment -> {
                    binding.mainNavbar.visibility = View.GONE
                }
                R.id.checkOutFragment -> {
                    binding.mainNavbar.visibility = View.GONE
                }
                else -> {
                    binding.mainNavbar.visibility = View.VISIBLE
                }
            }
        }

    }

}