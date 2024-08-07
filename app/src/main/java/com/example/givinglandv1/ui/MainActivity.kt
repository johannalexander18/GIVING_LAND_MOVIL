package com.example.givinglandv1.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.givinglandv1.AddFragment
import com.example.givinglandv1.MessageFragment
import com.example.givinglandv1.MyApplication
import com.example.givinglandv1.R
import com.example.givinglandv1.SolicitudFragment
import com.example.givinglandv1.UserFragment
import com.example.givinglandv1.ui.posts.HomeFragment
import com.example.givinglandv1.ui.posts.LocationViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var locationViewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializa el ViewModel
        locationViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(LocationViewModel::class.java)

        // Observa los cambios en los datos de ubicaciones
        locationViewModel.locations.observe(this, Observer { locations ->
            // Aquí puedes actualizar la UI con los datos de locations
            // O manejar la lógica que desees con los datos obtenidos
        })

        // Llama al método para cargar los datos tan pronto como se inicie la aplicación
        loadLocations()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val userLoggedIn = (application as MyApplication).isUserLoggedIn()
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.bottom_add, R.id.bottom_message, R.id.bottom_user -> {
                    if (!userLoggedIn) {
                        loadFragment(SolicitudFragment())
                    } else {
                        when(menuItem.itemId) {
                            R.id.bottom_add -> loadFragment(AddFragment())
                            R.id.bottom_message -> loadFragment(MessageFragment())
                            R.id.bottom_user -> loadFragment(UserFragment())
                        }
                    }
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.bottom_home
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragment, fragment)
            .commit()
    }

    private fun loadLocations() {
        // Inicia la carga de datos
        locationViewModel.loadLocations()
    }
}