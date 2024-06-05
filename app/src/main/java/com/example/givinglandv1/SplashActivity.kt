package com.example.givinglandv1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    //Tiempo que durara el splash screen
    private val splashTimeOut: Long = 4000  //4 segundos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            //despues de la spera carga la actividad
            startActivity(Intent(this, MainActivity::class.java))
            //cierre del splash
            finish()
        }, splashTimeOut)
    }
}