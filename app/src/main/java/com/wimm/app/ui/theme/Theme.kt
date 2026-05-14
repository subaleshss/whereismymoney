package com.wimm.app.ui.theme

import Black
import Blue
import DarkBg
import DarkBorder
import DarkSurface
import Gray100
import Gray300
import Gray700
import White
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

private val LightScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,

    secondary = Blue,
    onSecondary = White,

    background = White,
    onBackground = Black,

    surface = White,
    onSurface = Black,

    surfaceVariant = Gray100,
    onSurfaceVariant = Gray700,

    outline = Gray300
)

private val DarkScheme = darkColorScheme(
    primary = White,
    onPrimary = White,

    secondary = Blue,
    onSecondary = Black,

    background = DarkBg,
    onBackground = White,

    surface = DarkSurface,
    onSurface = White,

    surfaceVariant = Color(0xFF222222),
    onSurfaceVariant = Color(0xFFAAAAAA),

    outline = DarkBorder
)

@Composable
fun WhereismymoneyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkScheme else LightScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

