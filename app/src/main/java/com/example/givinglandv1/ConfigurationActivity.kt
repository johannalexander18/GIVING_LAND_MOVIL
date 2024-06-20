package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.givinglandv1.databinding.ActivityConfigurationBinding

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar listeners para cada botón
        binding.changePasswordButton.setOnClickListener {
            // Aquí puedes implementar la lógica para cambiar la contraseña si lo deseas
        }

        binding.deleteAccountButton.setOnClickListener {
            // Iniciar la actividad de ingreso de contraseña
            val intent = Intent(this, IngresarpasswordActivity::class.java)
            startActivity(intent)
        }

        binding.logoutButton.setOnClickListener {
            // Mostrar el fragmento de cerrar sesión como originalmente lo tenías
            val cerrarSesionFragment = CerrarsesionFragment()
            cerrarSesionFragment.show(supportFragmentManager, "CerrarSesionFragment")
        }

        // Verificar si debemos mostrar el diálogo de confirmación
        if (intent.getBooleanExtra("SHOW_CONFIRMATION_DIALOG", false)) {
            val confirmarEliminacionFragment = ConfirmacioneliminacionFragment()
            confirmarEliminacionFragment.show(supportFragmentManager, "ConfirmarEliminacionFragment")
        }
    }
}