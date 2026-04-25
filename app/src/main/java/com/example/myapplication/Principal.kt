package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityPrincipalBinding

class Principal : AppCompatActivity() {

    // iniciacion perezosa
    lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // binding de nuestra app
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}