package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityPrincipalBinding
import com.google.android.material.snackbar.Snackbar

class Principal : AppCompatActivity() {

    // iniciacion perezosa
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        // binding de nuestra app
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()


        initListeners()

    }

    private fun initVariables() {

        intent.extras.let {
            // manda a vacio
            var saludo = it?.getString("xx1")


            Snackbar.make(
                binding.urlText,
                saludo.toString(),
                Snackbar.LENGTH_LONG
            ).show()

        }


    }


    private fun initListeners() {

        binding.urlBtn.setOnClickListener {


            val url = binding.urlText.text.toString()

//            val i = Intent(Intent.ACTION_VIEW)
//
//            i.setData(Uri.parse(url))
//            startActivity(i)


            val gmmIntentUri = Uri.parse("geo:-0.1967636,-78.5038821")
            val mapInten = Intent(Intent.ACTION_VIEW)
            mapInten.setData(gmmIntentUri)

            mapInten.setPackage("com.google.android.apps.maps")
            startActivity(mapInten)




        }

    }


}