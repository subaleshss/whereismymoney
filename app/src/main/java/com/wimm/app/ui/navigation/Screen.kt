package com.wimm.app.ui.navigation

sealed class Screen(val route: String) {
    data object Login: Screen("login")
}