package com.example.givinglandv1.util

import android.content.Context
import android.content.SharedPreferences


class SharedPrefs(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var authToken: String?
        get() = prefs.getString("auth_token", null)
        set(value) {
            prefs.edit().putString("auth_token", value).apply()
        }
}