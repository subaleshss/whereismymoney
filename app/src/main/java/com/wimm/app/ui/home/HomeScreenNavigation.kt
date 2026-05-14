package com.wimm.app.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wimm.app.ui.login.LOGIN_ROUTE

const val HOME_ROUTE = "home"

fun NavController.toHomeScreen() {
    navigate(HOME_ROUTE) {
        popUpTo(LOGIN_ROUTE) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.homeScreenGraph() {
    composable(HOME_ROUTE) {
        HomeScreenRoute()
    }
}