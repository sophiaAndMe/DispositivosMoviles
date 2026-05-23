package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityPrincipalBinding
import com.example.myapplication.databinding.FragmentFirstFramentBinding


class FirstFrament : Fragment() {

    lateinit var binding: FragmentFirstFramentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstFramentBinding.inflate(
            layoutInflater,
            container,
            false)
        return binding.root
    }




}