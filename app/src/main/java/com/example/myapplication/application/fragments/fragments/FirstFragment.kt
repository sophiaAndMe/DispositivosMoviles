package com.example.myapplication.application.fragments.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.remote.dto.UserDtoRemote
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    private var db = Firebase.firestore

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
    }


    private fun initListeners(){



        binding.btnRegresar.setOnClickListener {

            val user = UserDtoRemote(
                "",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString()
                )


                // hilo optimizado, ya va hacer ansincrono en .IO
            // estara solo vivo hasta que el la activity este viva

                lifecycleScope.launch(Dispatchers.Main){
                    // salio del hilo principal y cuando termine regresara al hilo principal

                    val usnew = withContext(Dispatchers.IO) {
                        saveUser(user)
                    }

                    if(usnew.getOrNull() != null) {
                        Snackbar.make(
                            binding.nameUser,
                            "Usuario guardado correctamente",
                            Snackbar.LENGTH_SHORT)
                            .show()
                    }

                }
        }
    }

    // TODO: debe estar en una capa intermedia para mas eficiencia
    // inyecccion de dependencias
    private suspend fun saveUser( user: UserDtoRemote) : Result<UserDtoRemote> {
        var resp = db.collection("users")
            .add(user)
            // devuelve un result o un Drawaitvall
            .await().runCatching{user}
        return resp
    }

    private fun initVariables(){
         db = Firebase.firestore
    }

}