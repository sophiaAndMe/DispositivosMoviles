package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
    // pusheo prueba 2
    lateinit var binding : ActivityMobinLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // ya tiene todos los elementos que tenga el Id
        binding = ActivityMobinLoginBinding.inflate(layoutInflater)


        setContentView(binding.root)



        // set es actualizar
        binding.buttonLogin.setOnClickListener {

            var msg = ""

            if( binding.txtUsername.text.toString() == "admin"
                && binding.txtPassword.text.toString() == "root"){

                msg = "Usuario autenticado correctamente"

                val intent2 =  Intent(
                    this,
                    Principal::class.java)

                intent2.putExtra("xx1", "Hola Mundo")

                // siempre hay que poner esta al final para que recupere los datos
                startActivity(intent2)


            }else{
                msg = "Usuario incorrecto"

                Toast.makeText(
                    this,
                    msg,
                    Toast.LENGTH_LONG)
                    .show()
            }


        }

        // LOGO FACEBOOK
        binding.btnFacebook.setOnClickListener {
            // 1. Creamos el Intent con la dirección (Uri) de tu página o perfil
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/fblink?path=TU_PAGINA"))
            intent.setPackage("com.facebook.katana")

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // 2. Si no tiene la app de Facebook, la abre de forma segura en el navegador
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"))
                startActivity(webIntent)
            }

        }



    }


    // IMPORTANTE!
    // ES PARA CERRAR TODOS LOS PROCESOS QUE ESTEN PENDIENTES
    // corrutinas
    override fun onDestroy() {
        super.onDestroy()
    }



}