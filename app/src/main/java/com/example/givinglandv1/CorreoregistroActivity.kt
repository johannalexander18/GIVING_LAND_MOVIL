package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CorreoregistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_correoregistro)
        val sendCodeButton = findViewById<Button>(R.id.send_code2)
        sendCodeButton.setOnClickListener {
            val intent = Intent(this, CodigoregistroActivity::class.java)
            startActivity(intent)
        }
    }
}