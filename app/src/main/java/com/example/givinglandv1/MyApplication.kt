package com.example.givinglandv1

import android.app.Application

class MyApplication : Application() {
  //variable que permite mantener el estao de inicio de sesion del usuario
    private var userLoggedIn = false

    override fun onCreate() {
        super.onCreate()
    }
    //metodo que devuelve el valor de userloggedin
    fun isUserLoggedIn(): Boolean {
        return userLoggedIn
    }
    //metodo que permite  actualisar el valos de userloggedin
    fun setUserLoggedIn(loggedIn: Boolean) {
        userLoggedIn = loggedIn
    }
}