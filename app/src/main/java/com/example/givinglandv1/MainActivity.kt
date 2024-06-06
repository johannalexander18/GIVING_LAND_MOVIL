package com.example.givinglandv1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Selección de fragmento mediante su ID
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val userLoggedIn = (application as MyApplication).isUserLoggedIn()
            //evalua el bottom que fue seleccionado
            when(menuItem.itemId) {
                R.id.bottom_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                //si el usuario accede a uno delos siguientes bottones y no esta registrado se carga la vista de solicitudFragment
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

        // Al iniciar el MainActivity se selecciona el fragmento de Home
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.bottom_home
        }
    }

    // Reemplazar un fragmento por otro
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragment, fragment)
            .commit()
    }
}
