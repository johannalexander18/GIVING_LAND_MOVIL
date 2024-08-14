package com.example.givinglandv1.util

import android.content.Context
import android.content.SharedPreferences
import com.example.givinglandv1.data.model.posts.Category
import com.example.givinglandv1.data.model.posts.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedPrefs(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    var authToken: String?
        get() = prefs.getString("auth_token", null)
        set(value) {
            prefs.edit().putString("auth_token", value).apply()
        }

    var locations: String?
        get() = prefs.getString("locations", null)
        set(value) {
            prefs.edit().putString("locations", value).apply()
        }

    var categories: String?
        get() = prefs.getString("categories", null)
        set(value) {
            prefs.edit().putString("categories", value).apply()
        }

    fun getLocationById(locationId: Int): Location? {
        val json = locations
        if (json != null) {
            val type = object : TypeToken<List<Location>>() {}.type
            val locationList: List<Location> = Gson().fromJson(json, type)
            return locationList.find { it.id == locationId }
        }
        return null
    }
    fun getCategoryById(categoryId: Int): Category? {
        val json = categories
        if (json != null) {
            val type = object : TypeToken<List<Category>>() {}.type
            val categoryList: List<Category> = Gson().fromJson(json, type)
            return categoryList.find { it.id == categoryId }
        }
        return null
    }
    fun clearAuthToken() {
        prefs.edit().remove("auth_token").apply()
    }
}