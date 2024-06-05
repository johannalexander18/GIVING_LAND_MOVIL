package com.example.givinglandv1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        //SELECCION DE FRAGMENTO  MEDIANTE SI ID
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.bottom_add -> {
                    loadFragment(AddFragment())
                    true
                }
                R.id.bottom_message -> {
                    loadFragment(MessageFragment())
                    true
                }
                R.id.bottom_user -> {
                    loadFragment(UserFragment())
                    true
                }
                else -> false
            }

        }
        //AL INICIAR EL MAIN ACTIVITY SE SELCCIONA EL FRAGMENT DE HOME
        if (savedInstanceState == null){
            bottomNavigationView.selectedItemId = R.id.bottom_home
        }

    }

    // REMPLASAR UN FRAGMENTO POR OTRO
    private fun  loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedor_fragment, fragment)
            .commit()
    }
}