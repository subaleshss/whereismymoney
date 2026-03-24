package com.wimm.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WimmNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Login
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(Screen.Login.route) {

        }
    }
}