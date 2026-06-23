package com.example.myapplication.application.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnBackToLogin.setOnClickListener {
            finish()
        }

        // Check if user is already signed in
        if (auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerUser() {
        val email = binding.txtRegisterEmail.text.toString().trim()
        val password = binding.txtRegisterPassword.text.toString().trim()
        val confirmPassword = binding.txtConfirmPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return
        }

        binding.registerProgressBar.visibility = View.VISIBLE
        binding.btnRegister.isEnabled = false

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { verificationTask ->
                            binding.registerProgressBar.visibility = View.GONE
                            binding.btnRegister.isEnabled = true

                            if (verificationTask.isSuccessful) {
                                Toast.makeText(this, "Registro exitoso. Por favor verifica tu correo.", Toast.LENGTH_LONG).show()
                                auth.signOut() // Desloguear hasta que verifique
                                finish()
                            } else {
                                Log.e("RegisterActivity", "Verification email failed", verificationTask.exception)
                                Toast.makeText(this, "Error al enviar correo de verificación.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    binding.registerProgressBar.visibility = View.GONE
                    binding.btnRegister.isEnabled = true

                    val exception = task.exception
                    val errorMsg = when (exception) {
                        is FirebaseAuthException -> {
                            when (exception.errorCode) {
                                "ERROR_WEAK_PASSWORD" -> "La contraseña es muy débil (mínimo 6 caracteres)."
                                "ERROR_INVALID_EMAIL" -> "El formato del correo es inválido."
                                "ERROR_EMAIL_ALREADY_IN_USE" -> "Este correo ya está registrado."
                                "ERROR_CREDENTIAL_ALREADY_IN_USE" -> "Estas credenciales ya están en uso."
                                else -> "Error de Firebase: ${exception.errorCode} - ${exception.localizedMessage}"
                            }
                        }
                        is FirebaseNetworkException -> "Error de red. Verifica tu conexión a internet."
                        else -> exception?.localizedMessage ?: "Error desconocido"
                    }
                    Log.e("RegisterActivity", "Registration failed: ${exception?.message}", exception)
                    Toast.makeText(this, "Error: $errorMsg", Toast.LENGTH_LONG).show()
                }
            }
    }
}
