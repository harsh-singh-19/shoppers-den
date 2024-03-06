package com.example.shoppersden.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.shoppersden.R
import com.example.shoppersden.data.util.Resource
import com.example.shoppersden.ui.viewmodel.ProductViewModel
import com.example.shoppersden.ui.viewmodel.ProductViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModels { ProductViewModelFactory() }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

//        Log.d("####", "onCreate: ")
//
//        viewModel.products.observe(this) { products ->
//            when (products) {
//                is Resource.Error -> {
//                    Log.d("####", "Error: ${products.message}")
//                }
//
//                is Resource.Loading -> {
//                    Log.d("####", "Loading: ")
//                }
//
//                is Resource.Success -> {
//                    Log.d("####", "Success: ${products.data}")
//                }
//            }
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}