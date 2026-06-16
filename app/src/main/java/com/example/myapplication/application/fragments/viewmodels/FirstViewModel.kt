package com.example.myapplication.application.fragments.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.myapplication.logic.usercases.SaveUserUC
import com.example.myapplication.remote.dto.UserDtoRemote
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FirstViewModel : ViewModel (){


    // ponemos '_' para que no sea accesible
    val counterUI : LiveData<Int>
        get() = _counterUI

    // osea de AQUI, se obtiene los datos para el counterUI
    private var _counterUI = MutableLiveData<Int>()

    val userRemote get() = _userRemote

    private var _userRemote = MutableLiveData<UserDtoRemote>()

      fun contador() {

          // osea AHORA esta atado al counterUI, cada que cambia ese tambien
        viewModelScope.launch {
            var counter : Int = 0
            for(i in 1..15){
                delay(1000)
                counter++
                _counterUI.value = counter
            }
        }


    }
    //                 hilo optimizado, ya va hacer ansincrono en .IO
    //             estara solo vivo hasta que el la activity este viva

    fun guardarUsuario(user: UserDtoRemote,
                       db : FirebaseFirestore,
                       saveUserUC: SaveUserUC){



        // manda un hilo secundario
        viewModelScope.launch{
            // salio del hilo principal y cuando termine regresara al hilo principal

            val usnew = saveUserUC.saveUser(user, db)

            val usr = usnew.getOrNull()

            if( usr != null) {
                _userRemote.value = usr
            }
            else {
                (UserDtoRemote("",
                    "user no registrado",
                    ""))
            }

        }


    }






}