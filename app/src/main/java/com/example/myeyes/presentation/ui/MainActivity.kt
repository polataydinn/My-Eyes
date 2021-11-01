package com.example.myeyes.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myeyes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyEyes)
        setContentView(R.layout.activity_main)
    }
}