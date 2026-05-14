package com.wimm.app.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimm.app.domain.model.AuthResult
import com.wimm.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.None)
    val uiState = _uiState.asStateFlow()

    private fun signIn() {
        viewModelScope.launch {
            _uiState.update {
                LoginUiState.Loading
            }
            when(val result = authRepository.signIn()) {
                is AuthResult.Success -> {
                    _uiState.update {
                        LoginUiState.Success(result.user)
                    }
                }
                is AuthResult.Error -> {
                    _uiState.update {
                        LoginUiState.Error(result.message)
                    }
                }
                is AuthResult.Cancelled -> {
                    _uiState.update {
                        LoginUiState.None
                    }
                }
            }

        }
    }

    fun onEvent(event: LoginScreenEvent) {
        when (event) {
            is LoginScreenEvent.OnSignInClick -> {
                Log.d("login", "in viewmodel")
                signIn()
            }
        }

    }
}