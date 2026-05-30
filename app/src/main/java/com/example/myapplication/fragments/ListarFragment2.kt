package com.example.myapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFirstFramentBinding
import com.example.myapplication.databinding.FragmentListar2Binding
import com.example.myapplication.databinding.FragmentListar2ListBinding
import com.example.myapplication.fragments.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ListarFragment2 : Fragment() {


    lateinit var binding: FragmentListar2Binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListar2Binding.inflate(
            layoutInflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {

        binding.btnGoToListar.setOnClickListener {

            findNavController().navigate(R.id.action_fragmentDos_to_firstFrament2)
        }

    }
}