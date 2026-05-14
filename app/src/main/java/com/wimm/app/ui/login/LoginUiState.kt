package com.wimm.app.ui.login

import com.wimm.app.domain.model.User

sealed interface LoginUiState {

    data object Loading: LoginUiState
    data class Error(val message: String): LoginUiState
    data object None: LoginUiState
    data class Success(val user: User): LoginUiState
}