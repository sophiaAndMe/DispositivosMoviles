package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDosBinding

class FragmentDos : Fragment() {

    lateinit var binding: FragmentDosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDosBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    @SuppressLint("CheckResult")
    private fun initListeners() {

        binding.btnRegresar.setOnClickListener {

           // findNavController().navigate(R.id.action_fragmentDos_to_firstFrament2)

            /*
                REVISAR - ESTO SIRVE PARA PASAR DATOS ENTRE FRAGMENTS
             */


            FragmentDosDirections.actionFragmentDosToFirstFrament2()
        }


    }


}