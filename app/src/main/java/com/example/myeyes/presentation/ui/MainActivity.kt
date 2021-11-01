package com.example.myeyes.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myeyes.R
import com.example.myeyes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyEyes)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.background = null
    }
}