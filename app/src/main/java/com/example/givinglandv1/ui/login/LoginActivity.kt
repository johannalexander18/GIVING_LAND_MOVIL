package com.example.givinglandv1.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.givinglandv1.CorreoActivity
import com.example.givinglandv1.ui.register.RegistroActivity
import com.example.givinglandv1.ui.MainActivity
import com.example.givinglandv1.MyApplication
import com.example.givinglandv1.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, CorreoActivity::class.java)
            startActivity(intent)
        }
        binding.forgotRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            loginViewModel.login(email, password)
        }

        loginViewModel.loginResult.observe(this) { result ->
            if (result.success) {
                val mainActivityIntent = Intent(this, MainActivity::class.java)
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(mainActivityIntent)
                (application as MyApplication).setUserLoggedIn(true)
                Toast.makeText(this, "Token guardado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}