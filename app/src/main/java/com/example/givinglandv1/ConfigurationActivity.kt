package com.example.givinglandv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.givinglandv1.databinding.ActivityConfigurationBinding

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar listeners para cada botón
        binding.changePasswordButton.setOnClickListener {
            // Lógica para cambiar contraseña
            Toast.makeText(this, "Cambiar Contraseña seleccionado", Toast.LENGTH_SHORT).show()
        }

        binding.deleteAccountButton.setOnClickListener {
            // Lógica para eliminar cuenta
            Toast.makeText(this, "Eliminar Cuenta seleccionado", Toast.LENGTH_SHORT).show()
        }

        // Configurar el botón de cerrar sesión
        binding.logoutButton.setOnClickListener {
            // Mostrar el fragmento CerrarSesionFragment
            val cerrarSesionFragment = CerrarsesionFragment()
            cerrarSesionFragment.show(supportFragmentManager, "CerrarSesionFragment")
        }
    }
}