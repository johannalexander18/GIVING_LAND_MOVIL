package com.example.givinglandv1.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.givinglandv1.data.repository.UserRepository
import kotlinx.coroutines.launch

class DeleteAccountViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _deleteAccountResult = MutableLiveData<Result<String>>()
    val deleteAccountResult: LiveData<Result<String>> = _deleteAccountResult

    fun deleteAccount(password: String) {
        viewModelScope.launch {
            _deleteAccountResult.value = userRepository.deleteAccount(password)
        }
    }
}