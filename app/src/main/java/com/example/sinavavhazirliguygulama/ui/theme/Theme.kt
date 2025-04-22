package com.example.sinavavhazirliguygulama.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Açık tema renk şeması
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF3F51B5),          // İndigo
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8EAF6), // Açık İndigo
    onPrimaryContainer = Color(0xFF121858),
    secondary = Color(0xFF4CAF50),        // Yeşil
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE8F5E9),
    onSecondaryContainer = Color(0xFF1B5E20),
    tertiary = Color(0xFFF44336),         // Kırmızı
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFEBEE),
    onTertiaryContainer = Color(0xFF8E0000),
    error = Color(0xFFB00020),
    background = Color(0xFFF5F5F5),
    onBackground = Color(0xFF121212),
    surface = Color.White,
    onSurface = Color(0xFF121212),
    outline = Color(0xFF6E6E6E),
    surfaceVariant = Color(0xFFE6E6E6),
    onSurfaceVariant = Color(0xFF505050)
)

// Koyu tema renk şeması
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF7986CB),          // Açık İndigo
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF121858), // Koyu İndigo
    onPrimaryContainer = Color(0xFFE8EAF6),
    secondary = Color(0xFF81C784),        // Açık Yeşil
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF1B5E20),
    onSecondaryContainer = Color(0xFFE8F5E9),
    tertiary = Color(0xFFE57373),         // Açık Kırmızı
    onTertiary = Color.Black,
    tertiaryContainer = Color(0xFF8E0000),
    onTertiaryContainer = Color(0xFFFFEBEE),
    error = Color(0xFFCF6679),
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    outline = Color(0xFF9E9E9E),
    surfaceVariant = Color(0xFF2C2C2C),
    onSurfaceVariant = Color(0xFFDDDDDD)
)

@Composable
fun SinavHazirlikUygulamasiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}