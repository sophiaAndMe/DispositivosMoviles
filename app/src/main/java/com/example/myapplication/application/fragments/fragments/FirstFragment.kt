package com.example.myapplication.application.fragments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.application.fragments.viewmodels.FirstViewModel
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.logic.usercases.GetAllUserUC
import com.example.myapplication.logic.usercases.SaveUserUC
import com.example.myapplication.remote.dto.UserDtoRemote
import com.example.myapplication.repositories.connections.remote.UserRemoteImpl
import com.example.myapplication.repositories.connections.remote.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.getValue


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    private var db = Firebase.firestore

    // voy a atar el ciclo de vida a esta clase kt
    private val firstVM by viewModels<FirstViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initVariables()
        initObserver()
    }


    private fun initListeners(){



        binding.btnRegresar.setOnClickListener {

            val user = UserDtoRemote(
                "11",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString()
                )


            // porque vamos a modificar la vista
            lifecycleScope.launch (Dispatchers.Main) {
                firstVM.contador()
            }

            lifecycleScope.launch (Dispatchers.Main) {
                firstVM.guardarUsuario(user,
                    SaveUserUC(
                        UserRepository(
                            UserRemoteImpl(db)
                    )
                )
                )
            }

            lifecycleScope.launch {

                firstVM.listarUsuario(
                    GetAllUserUC(
                        UserRepository(
                            UserRemoteImpl(db)
                        )
                    )
                )
            }

        }
    }

    // todo lo que diga observe hay que pasarle a una funcion
    private fun initObserver(){


        // empieza a observar el dato, si es que cambia lo vera!
        firstVM.counterUI.observe(viewLifecycleOwner){
            binding.contadorTxt.text = it.toString()
        }

        firstVM.userRemote.observe(viewLifecycleOwner) {

            Snackbar.make(
                binding.nameUser,
                "Usuario guardado correctamente",
                Snackbar.LENGTH_LONG)
                .show()
        }

        firstVM.listUsuarios.observe(viewLifecycleOwner) {

            user ->
                user.forEach {
                    Log.d("TAG" , it.toString())
                }


        }

    }



    private fun initVariables(){
         db = Firebase.firestore
    }

}