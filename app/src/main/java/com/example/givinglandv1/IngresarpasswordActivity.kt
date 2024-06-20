package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.givinglandv1.databinding.ActivityIngresarpasswordBinding

class IngresarpasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngresarpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarpasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            // Simula el ingreso de una contraseña válida y muestra la confirmación
            val intent = Intent(this, ConfigurationActivity::class.java)
            intent.putExtra("SHOW_CONFIRMATION_DIALOG", true)
            startActivity(intent)
            finish()
        }
    }
}
