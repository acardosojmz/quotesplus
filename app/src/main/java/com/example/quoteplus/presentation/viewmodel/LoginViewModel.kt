package com.example.quoteplus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quoteplus.domain.model.LoginUiState
import com.example.quoteplus.domain.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onAccountChange(account: String) {
        _uiState.value = _uiState.value.copy(account = account)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun login() {
        viewModelScope.launch {
            val user = authenticate(_uiState.value.account, _uiState.value.password)
            _uiState.value = _uiState.value.copy(user = user, isLoggedIn = user != null)
        }
    }

    private suspend fun authenticate(account: String, password: String): UserModel? {
        // Simular una autenticación
        return if (account == "user" && password == "password") {
            UserModel(id = 1, account = account, password = password)
        } else {
            null
        }
    }
}