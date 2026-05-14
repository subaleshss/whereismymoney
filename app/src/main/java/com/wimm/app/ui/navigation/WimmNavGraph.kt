package com.wimm.app.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wimm.app.ui.home.HOME_ROUTE
import com.wimm.app.ui.home.homeScreenGraph
import com.wimm.app.ui.home.toHomeScreen
import com.wimm.app.ui.login.LOGIN_ROUTE
import com.wimm.app.ui.login.LoginScreen
import com.wimm.app.ui.login.LoginScreenRoute
import com.wimm.app.ui.login.loginNavGraph

@Composable
fun WimmNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = LOGIN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        loginNavGraph {
            navController.toHomeScreen()
        }

        homeScreenGraph()
    }
}