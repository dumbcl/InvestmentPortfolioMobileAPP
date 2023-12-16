package com.example.investmentportfolio.ui

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.investmentportfolio.R
import com.example.investmentportfolio.di.initKoin


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {}
        setContentView(R.layout.activity_main)
    }
}