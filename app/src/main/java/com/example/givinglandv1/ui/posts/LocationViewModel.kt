package com.example.givinglandv1.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.posts.Location
import com.example.givinglandv1.data.repository.LocationRepository
import com.example.givinglandv1.util.SharedPrefs
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LocationRepository(RetrofitInstance.api)
    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> get() = _locations

    init {
        // Opcionalmente, puedes cargar los datos aquí también
        // loadLocations()
    }

    fun loadLocations() {
        viewModelScope.launch {
            val locations = repository.getLocations()
            locations?.let {
                _locations.postValue(it)
                saveLocationsToPreferences(it)
            }
        }
    }

    private fun saveLocationsToPreferences(locations: List<Location>) {
        val sharedPrefs = SharedPrefs(getApplication())
        val json = Gson().toJson(locations)
        sharedPrefs.locations = json  // Usa 'locations' para guardar los datos como string JSON
    }
}