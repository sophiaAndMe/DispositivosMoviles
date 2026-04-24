package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMobinLoginBinding

class MobinLogin : AppCompatActivity() {


    // Son lazy -> solo se crean cuando se necesita
    // tambien vemos ambito (scope), estan esta a nivel de clase
    // pusheo prueba
    lateinit var binding : ActivityMobinLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // ya tiene todos los elementos que tenga el Id
        binding = ActivityMobinLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)



        // set es actualizar
        binding.buttonLogin.setOnClickListener {

            var msg = ""

            if( binding.txtUsername.text.toString() == "admin" && binding.txtPassword.text.toString() == "root"){

                msg = "Usuario autenticado correctamente"

                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_LONG).show()
            }else{
                msg = "Usuario incorrecto"

                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_LONG).show()
            }




        }





    }

}