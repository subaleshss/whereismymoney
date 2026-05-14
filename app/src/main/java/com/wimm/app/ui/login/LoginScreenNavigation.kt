package com.wimm.app.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wimm.app.ui.navigation.Screen

const val LOGIN_ROUTE = "login"

fun NavController.navigateToLogin() {
    navigate(LOGIN_ROUTE)
}

fun NavGraphBuilder.loginNavGraph(
    onLoginSuccess: () -> Unit
) {
    composable(LOGIN_ROUTE) {
        LoginScreenRoute(
            onLoginSuccess = onLoginSuccess
        )
    }
}