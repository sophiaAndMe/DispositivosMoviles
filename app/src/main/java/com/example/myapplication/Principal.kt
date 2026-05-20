package com.example.myapplication

import android.app.AlertDialog
import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CustomAdapter
import com.example.myapplication.databinding.ActivityMobinLoginBinding
import com.example.myapplication.databinding.ActivityPrincipalBinding
import com.example.myapplication.dto.Empresas
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.snackbar.Snackbar
import java.security.Principal

class Principal : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    // iniciacion perezosa
    private lateinit var binding: ActivityPrincipalBinding

    var adapterRecyclerView = CustomAdapter(
        {getName(it)},
        {deleteEmpresas(it)}
    )



    override fun onCreate(savedInstanceState: Bundle?) {

        // binding de nuestra app
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVariables()
        initListeners()

    }


    override fun onDestroy() {
        super.onDestroy()
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






        // RECYCLERVIEW

        /*
        * por cada objeto en la lista, tiene un simple layaout por cada uno y con
        * el adapter los unes
       */

        var optionsEmpresas = listOf<Empresas>(
            Empresas(
                "Youtube",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdWAHLE_HOsD6iFbpqtYy9hRMTwP9fYi3zEQ&s",
                "https://www.youtube.com"
            ),
            Empresas(
                "Google",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiawM5VCV4cRb5Sbz5wIWtOhTLojJ_kJDsTA&s",
                "https://www.google.com.ec"
            ),
            Empresas(
                "Instagram",
                "https://png.pngtree.com/element_our/sm/20180630/sm_5b37de3263964.jpg",
                "https://www.instagram.com/"
            )
        )


        /// para al app mobil, podriamos hacer esto y llamar SOLO  a una planta, y evitariamos cargarlas en una
        /// base de datos, directamente llamarla y obtener en su formato json lo que necesitamos
        binding.RvUrls.adapter = adapterRecyclerView
        binding.RvUrls.layoutManager = GridLayoutManager(this, 2)
        adapterRecyclerView.submitList(optionsEmpresas)

    }


    fun getName(emp : Empresas){

        val i = Intent(Intent.ACTION_WEB_SEARCH)
        i.putExtra(SearchManager.QUERY, emp.name)
        startActivity(i)

    }

    fun deleteEmpresas(emp : Empresas){

        val listaActual = adapterRecyclerView.currentList.toMutableList()
        listaActual.remove(emp)
        adapterRecyclerView.submitList(listaActual)

    }
    private fun initListeners() {

        // BUTTON
        binding.urlBtn.setOnClickListener {

            binding.urlText.text.toString()
            val gmmIntentUri = Uri.parse("geo:-0.1967636,-78.5038821")
            val mapInten = Intent(Intent.ACTION_VIEW)
            mapInten.setData(gmmIntentUri)

            mapInten.setPackage("com.google.android.apps.maps")
            startActivity(mapInten)

        }

        // AVISOS
        binding.logout.setOnClickListener {

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
        }




    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        Toast.makeText(this,
            "Posicion seleccionada es ${position}",
            Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}