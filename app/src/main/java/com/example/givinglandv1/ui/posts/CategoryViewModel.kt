package com.example.givinglandv1.ui.posts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.api.RetrofitInstance
import com.example.givinglandv1.data.model.posts.Category
import com.example.givinglandv1.data.repository.CategoryRepository
import com.example.givinglandv1.util.SharedPrefs
import com.google.gson.Gson
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CategoryRepository(RetrofitInstance.api)
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        // Opcionalmente, puedes cargar los datos aquí también
        // loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            val categories = repository.getCategories()
            categories?.let {
                _categories.postValue(it)
                saveCategoriesToPreferences(it)
            }
        }
    }

    private fun saveCategoriesToPreferences(categories: List<Category>) {
        val sharedPrefs = SharedPrefs(getApplication())
        val json = Gson().toJson(categories)
        sharedPrefs.categories = json
    }
}