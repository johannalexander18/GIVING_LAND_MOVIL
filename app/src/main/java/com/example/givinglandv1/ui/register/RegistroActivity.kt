package com.example.givinglandv1.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.givinglandv1.ui.MainActivity
import com.example.givinglandv1.MyApplication
import com.example.givinglandv1.databinding.ActivityRegistroBinding


class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.sendCode2.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.emailRecovery.text.toString()
            val password = binding.password.text.toString()
            val passwordConfirmation = binding.passwordConfirmation.text.toString()
            if (validateInputs(name, email, password, passwordConfirmation)) {
                registerViewModel.register(name, email, password, passwordConfirmation)
            }
        }

        registerViewModel.registerResult.observe(this, Observer { result ->
            if (result.success) {
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                mainActivityIntent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(mainActivityIntent)
                (application as MyApplication).setUserLoggedIn(true)
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validateInputs(name: String, email: String, password: String, passwordConfirmation: String): Boolean {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password != passwordConfirmation) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            return false
        }
        // Agrega otras validaciones si es necesario
        return true
    }
}