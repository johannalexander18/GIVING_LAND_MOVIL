package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val forgotPassword = findViewById<TextView>(R.id.forgot_password)
        forgotPassword.setOnClickListener {
            val intent = Intent(this, CorreoActivity::class.java)
            startActivity(intent)
        }
        val forgotregistro = findViewById<TextView>(R.id.forgot_registro)
        forgotregistro.setOnClickListener {
            val intent = Intent(this, CorreoregistroActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            // Simular inicio de sesión
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(mainActivityIntent)
            (application as MyApplication).setUserLoggedIn(true)
        }
    }
}