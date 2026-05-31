package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.security.Principal

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         initListeners()

    }

    fun initListeners(){
        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.mn_home -> {
                    true }
                R.id.mn_pag1 -> {
                    true }
                R.id.mn_pag2 -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Cerrar sesion")
                        .setMessage("¿Esta seguro de salir de la aplicacion?")
                        .setCancelable(true)
                        .setPositiveButton("Si"){
                                dialog , id ->
                            val intent = Intent(this, Principal::class.java)
                            startActivity(intent)
                        }
                        .setNegativeButton("No"){
                                dialog, id -> dialog.dismiss()
                        }.setNeutralButton("Cancelar"){
                                dialog, id -> dialog.dismiss()
                        }
                        .show()
                    true }
                else -> false
            }


        }
    }
}