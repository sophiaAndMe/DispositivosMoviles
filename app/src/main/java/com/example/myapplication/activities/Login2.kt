package com.example.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
