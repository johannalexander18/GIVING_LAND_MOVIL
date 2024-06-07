package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class passwordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password)

        val loginButton: Button = findViewById(R.id.finish_button)
        loginButton.setOnClickListener {
            // Simular inicio de sesión
            val mainActivityIntent = Intent(this, MainActivity::class.java)
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(mainActivityIntent)
            (application as MyApplication).setUserLoggedIn(true)
        }
    }
}