package com.example.myeyes.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myeyes.R
import com.example.myeyes.databinding.ActivityMainBinding
import com.example.myeyes.presentation.ui.fragment.CameraFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyEyes)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.background = null

        val navView: BottomNavigationView = binding.bottomNavigation
        navController = findNavController(R.id.fragmentContainerView)

        AppBarConfiguration.Builder(
            setOf(
                R.id.homeFragment,
                R.id.personFragment
            )
        ).build()
        navView.setupWithNavController(navController)

        binding.camera.setOnClickListener {
            val cameraFragment = CameraFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, cameraFragment)
                .commit()
        }
    }
}