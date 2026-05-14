package com.wimm.app.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreenRoute(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value) {
        if (uiState.value is LoginUiState.Success) {
            onLoginSuccess.invoke()
        }
    }
    LoginScreen(
        uiState = uiState.value,
        onEvent = viewModel::onEvent
    )
}