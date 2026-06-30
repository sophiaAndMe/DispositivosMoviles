package com.example.myapplication.application.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.cloudinary.android.MediaManager
import com.example.myapplication.application.viewmodels.FirstViewModel
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.logic.usercases.GetAllUserUC
import com.example.myapplication.logic.usercases.SaveUserUC
import com.example.myapplication.remote.dto.UserDtoRemote
import com.example.myapplication.repositories.connections.CloudnaryService
import com.example.myapplication.repositories.connections.local.LocalDataBase
import com.example.myapplication.repositories.connections.remote.UserRemoteImpl
import com.example.myapplication.repositories.connections.remote.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlin.getValue


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    private var db = Firebase.firestore

    private lateinit var dbLocal : LocalDataBase

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



        binding.btnGuardar.setOnClickListener {


            viewGalery.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly))


            val user = UserDtoRemote(
                "11",
                binding.nameUser.text.toString(),
                binding.lastnameUser.text.toString(),
                null
                )



            // porque vamos a modificar la vista
            lifecycleScope.launch (Dispatchers.Main) {
                firstVM.contador()
            }

            lifecycleScope.launch (Dispatchers.Main) {
                firstVM.guardarUsuario(user,
                    SaveUserUC(
                        UserRepository(
                            UserRemoteImpl(db),
                            dbLocal
                    )
                )
                )
            }

            lifecycleScope.launch {

                firstVM.listarUsuario(
                    GetAllUserUC(
                        UserRepository(
                            UserRemoteImpl(db),
                            dbLocal
                        )
                    )
                )
            }

        }

        binding.btnAPI.setOnClickListener {

            // CORRUTINA
            lifecycleScope.launch(Dispatchers.IO) {

                     firstVM.getUserTypi()
            }
        }

        binding.btnSubir.setOnClickListener {




        }



    }


    //logica de Activity for result
    private val viewGalery = registerForActivityResult(

        ActivityResultContracts.PickVisualMedia() // usa Photo Picker para seleccionar multimedia
    ) {
        uri ->
            if(uri != null) {
                // guardar en archivo temporal
                val isValid = guardarArchivoTemporal(uri)
                if( isValid) {
                    Toast.makeText(
                        requireContext(), // recupera de cualquier lado
                        "El archivo esta listo para ser subido",
                        Toast.LENGTH_SHORT
                    ).show()

                    subirImagen()
                }else{
                    Toast.makeText(
                        requireContext(), // recupera de cualquier lado
                        "Ocurrio un ERROR!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun guardarArchivoTemporal(uri : Uri): Boolean {

        return try {
            val contentResolver = requireContext().contentResolver
            val temporalFile = File(requireContext().cacheDir, "img_temp.jpg")
            // combierte a bytes
            val inputStream = contentResolver.openInputStream(uri) ?: return false
            // lo guarda con el nombre "img_temp.jpg"
            val outputStream = FileOutputStream(temporalFile)

            inputStream?.copyTo(outputStream)

            inputStream?.close()
            outputStream.close()

             true

        }catch (ex : Exception){
            Log.e("ERRFILE", ex.message.toString())
            false
        }

    }

    private fun subirImagen(){
        val archivoCache = File(requireContext().cacheDir, "img_temp.jpg")

        CloudnaryService.subirImagenFirmada(archivoCache){
            isExitoso, resultado ->

            lifecycleScope.launch(Dispatchers.IO) {
                val resultText = if(isExitoso){
                    "La imagen se subio correctamente en ${resultado}"
                }else{
                    "Error: ${resultado}"
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        resultText,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }



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

        firstVM.typiUser.observe(viewLifecycleOwner) { it ->
            it?.forEach {
                Log.d("ITEMS", it.name)

            }
        }

    }

    private fun initVariables(){
         db = Firebase.firestore

        val config = mapOf(
            "cloud_name" to "deqhd3bmp",
            "api_key" to "188973848385489",
            "api_secret" to "bmPFYmcccVKbOhp5g0U6LyHn8aE"
        )

        MediaManager.init(
            requireContext(),
            config
        )


        dbLocal = Room.databaseBuilder(
            requireContext(),
            LocalDataBase::class.java, "database-name"
        ).build()

        dbLocal.profilesDao().getAllProfiles()

    }







}