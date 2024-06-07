package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CorreoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_correo)
        val sendCodeButton = findViewById<Button>(R.id.send_code)
        sendCodeButton.setOnClickListener {
            val intent = Intent(this, CodigoActivity::class.java)
            startActivity(intent)
        }
    }
}