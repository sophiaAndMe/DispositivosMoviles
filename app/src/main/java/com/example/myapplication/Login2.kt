package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityLogin2Binding

class Login2 : AppCompatActivity() {

    lateinit var binding : ActivityLogin2Binding



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


            binding = ActivityLogin2Binding.inflate(layoutInflater)

            setContentView(binding.root)

        }





    fun initVariable() {

    }

    fun initListeners() {


    }


}
