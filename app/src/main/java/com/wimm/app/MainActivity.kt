package com.wimm.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wimm.app.ui.home.HOME_ROUTE
import com.wimm.app.ui.login.LOGIN_ROUTE
import com.wimm.app.ui.main.MainViewModel
import com.wimm.app.ui.navigation.WimmNavGraph
import com.wimm.app.ui.theme.WhereismymoneyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        val viewModel : MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition {
            !viewModel.uiState.value.isReady
        }
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            WhereismymoneyTheme {
                WimmNavGraph(
                    startDestination = if (uiState.isLoggedIn){
                        HOME_ROUTE
                    } else {
                        LOGIN_ROUTE
                    }
                )
            }
        }
    }
}