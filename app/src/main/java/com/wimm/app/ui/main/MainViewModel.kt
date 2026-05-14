package com.wimm.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimm.app.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    var uiState = MutableStateFlow(MainActivityUiState())
        private set

    init {
        checkUserLoginStatus()
    }

    private fun checkUserLoginStatus() {
        viewModelScope.launch {
            val isLoggedIn = authRepository.isLoggedIn()
            uiState.value = uiState.value.copy(
                isReady = true,
                isLoggedIn = true
            )
        }
    }
}