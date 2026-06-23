package com.example.myapplication.application.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMobinLoginBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

class MobinLogin : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var credentialManager: CredentialManager

    // Son lazy -> solo se crean cuando se necesita
    // tambien vemos ambito (scope), estan esta a nivel de clase
    // pusheo prueba 2
    lateinit var binding : ActivityMobinLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Initialize Credential Manager
        credentialManager = CredentialManager.create(this)

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
                    MainActivity::class.java)

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

        binding.btnGoogle.setOnClickListener {
            signInWithGoogle()
        }

        binding.btnGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



    }

    private fun signInWithGoogle() {
        binding.progressBar.visibility = View.VISIBLE
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.default_web_client_id))
            .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@MobinLogin,
                )
                handleSignIn(result.credential)
            } catch (e: GetCredentialException) {
                binding.progressBar.visibility = View.GONE
                Log.e("MobinLogin", "Google Sign-In failed", e)
                Toast.makeText(this@MobinLogin, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleSignIn(credential: Credential) {
        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val idToken = googleIdTokenCredential.idToken
            firebaseAuthWithGoogle(idToken)
        } else {
            binding.progressBar.visibility = View.GONE
            Log.e("MobinLogin", "Unexpected credential type")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                binding.progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Bienvenido ${user?.displayName}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.e("MobinLogin", "Firebase Auth failed", task.exception)
                    Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show()
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