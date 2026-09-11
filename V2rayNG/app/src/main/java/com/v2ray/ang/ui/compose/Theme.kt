package com.v2ray.ang.ui.compose

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.v2ray.ang.AppConfig
import com.v2ray.ang.handler.MmkvManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ═══════════════════════════════════════════
// Pars VIP - Light Colors (نئونی روشن)
// ═══════════════════════════════════════════
private val LightColor = lightColorScheme(
    primary = Color(0xFF00FFEA),                 // Neon Cyan
    onPrimary = Color(0xFF000000),               // Black
    primaryContainer = Color(0xFF003333),        // Dark Cyan
    onPrimaryContainer = Color(0xFF00FFEA),      // Neon Cyan
    secondary = Color(0xFF00FF88),               // Neon Green
    onSecondary = Color(0xFF000000),             // Black
    secondaryContainer = Color(0xFF003322),      // Dark Green
    onSecondaryContainer = Color(0xFF00FF88),    // Neon Green
    tertiary = Color(0xFFFF00AA),                // Neon Pink
    onTertiary = Color(0xFFFFFFFF),              // White
    tertiaryContainer = Color(0xFF330022),       // Dark Pink
    onTertiaryContainer = Color(0xFFFF00AA),     // Neon Pink
    error = Color(0xFFFF3366),                   // Neon Red
    errorContainer = Color(0xFF330011),          // Dark Red
    onError = Color(0xFFFFFFFF),                 // White
    onErrorContainer = Color(0xFFFF3366),        // Neon Red
    background = Color(0xFF0A0A0A),              // Near Black
    onBackground = Color(0xFFFFFFFF),            // White
    surface = Color(0xFF141414),                 // Dark Surface
    onSurface = Color(0xFFFFFFFF),               // White
    surfaceVariant = Color(0xFF1A1A1A),          // Card Dark
    onSurfaceVariant = Color(0xFFAAAAAA),        // Light Gray
    outline = Color(0xFF00FFEA),                 // Neon Cyan
    outlineVariant = Color(0xFF333333),          // Dark Gray
    inverseSurface = Color(0xFFE6E1E5),          // Light
    inverseOnSurface = Color(0xFF1C1B1F),        // Near Black
    inversePrimary = Color(0xFF00FFEA),          // Neon Cyan
    scrim = Color(0xFF000000),                   // Black
    surfaceTint = Color(0xFF00FFEA),             // Neon Cyan
    surfaceContainerLowest = Color(0xFF000000),  // Pure Black
    surfaceContainerLow = Color(0xFF0A0A0A),     // Near Black
    surfaceContainer = Color(0xFF141414),        // Dark Surface
    surfaceContainerHigh = Color(0xFF1A1A1A),    // Card Dark
    surfaceContainerHighest = Color(0xFF222222), // Lighter Dark
)

// ═══════════════════════════════════════════
// Pars VIP - Dark Colors (نئونی مشکی - اصلی)
// ═══════════════════════════════════════════
private val DarkColor = darkColorScheme(
    primary = Color(0xFF00FFEA),                 // Neon Cyan
    onPrimary = Color(0xFF000000),               // Black
    primaryContainer = Color(0xFF003333),        // Dark Cyan
    onPrimaryContainer = Color(0xFF00FFEA),      // Neon Cyan
    secondary = Color(0xFF00FF88),               // Neon Green
    onSecondary = Color(0xFF000000),             // Black
    secondaryContainer = Color(0xFF003322),      // Dark Green
    onSecondaryContainer = Color(0xFF00FF88),    // Neon Green
    tertiary = Color(0xFFFF00AA),                // Neon Pink
    onTertiary = Color(0xFFFFFFFF),              // White
    tertiaryContainer = Color(0xFF330022),       // Dark Pink
    onTertiaryContainer = Color(0xFFFF00AA),     // Neon Pink
    error = Color(0xFFFF3366),                   // Neon Red
    errorContainer = Color(0xFF330011),          // Dark Red
    onError = Color(0xFFFFFFFF),                 // White
    onErrorContainer = Color(0xFFFF3366),        // Neon Red
    background = Color(0xFF0A0A0A),              // Near Black
    onBackground = Color(0xFFFFFFFF),            // White
    surface = Color(0xFF141414),                 // Dark Surface
    onSurface = Color(0xFFFFFFFF),               // White
    surfaceVariant = Color(0xFF1A1A1A),          // Card Dark
    onSurfaceVariant = Color(0xFFAAAAAA),        // Light Gray
    outline = Color(0xFF00FFEA),                 // Neon Cyan
    outlineVariant = Color(0xFF333333),          // Dark Gray
    inverseSurface = Color(0xFFE6E1E5),          // Light
    inverseOnSurface = Color(0xFF1C1B1F),        // Near Black
    inversePrimary = Color(0xFF00FFEA),          // Neon Cyan
    scrim = Color(0xFF000000),                   // Black
    surfaceTint = Color(0xFF00FFEA),             // Neon Cyan
    surfaceContainerLowest = Color(0xFF000000),  // Pure Black
    surfaceContainerLow = Color(0xFF0A0A0A),     // Near Black
    surfaceContainer = Color(0xFF141414),        // Dark Surface
    surfaceContainerHigh = Color(0xFF1A1A1A),    // Card Dark
    surfaceContainerHighest = Color(0xFF222222), // Lighter Dark
)

// ═══════════════════════════════════════════
// Semantic Colors - Pars VIP
// ═══════════════════════════════════════════
val colorPing = Color(0xFF00FF88)               // Neon Green
val colorPingRed = Color(0xFFFF3366)            // Neon Red
val colorConfigType = Color(0xFFFF00AA)         // Neon Pink
val colorFabActive = Color(0xFF00FF88)          // Neon Green (دکمه فعال)
val colorFabInactiveLight = Color(0xFFFF3366)   // Neon Red
val colorFabInactiveDark = Color(0xFFFF3366)    // Neon Red
val dividerColorLight = Color(0xFF1A1A1A)       // Dark
val dividerColorDark = Color(0xFF1A1A1A)        // Dark

// Toast Colors 70% - Pars VIP
val toastNormalBgLight = Color(0xB31A1A1A)      // Dark Card
val toastNormalBgDark = Color(0xB31A1A1A)       // Dark Card
val toastSuccessBg = Color(0xB300FF88)          // Neon Green
val toastErrorBg = Color(0xB3FF3366)            // Neon Red
val toastInfoBg = Color(0xB300FFEA)             // Neon Cyan
val toastIconCircleBg = Color(0x3300FFEA)       // Semi-transparent Cyan
val toastTextColor = Color(0xFFFFFFFF)          // White

object ThemeManager {
    private val _themeMode = MutableStateFlow(
        MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "2") ?: "2"
    )
    val themeMode: StateFlow<String> = _themeMode.asStateFlow()

    private val _dynamicColorEnabled = MutableStateFlow(
        MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, false)
    )
    val dynamicColorEnabled: StateFlow<Boolean> = _dynamicColorEnabled.asStateFlow()

    fun setThemeMode(mode: String) {
        MmkvManager.encodeSettings(AppConfig.PREF_UI_MODE_NIGHT, mode)
        _themeMode.value = mode
    }

    fun setDynamicColorEnabled(enabled: Boolean) {
        MmkvManager.encodeSettings(AppConfig.PREF_DYNAMIC_COLOR, enabled)
        _dynamicColorEnabled.value = enabled
    }

    fun refresh() {
        _themeMode.value =
            MmkvManager.decodeSettingsString(AppConfig.PREF_UI_MODE_NIGHT, "2") ?: "2"
        _dynamicColorEnabled.value =
            MmkvManager.decodeSettingsBool(AppConfig.PREF_DYNAMIC_COLOR, false)
    }
}

@Composable
fun resolveDarkTheme(): Boolean {
    val mode by ThemeManager.themeMode.collectAsState()
    return when (mode) {
        "1" -> false
        "2" -> true
        else -> isSystemInDarkTheme()
    }
}

val LocalDarkTheme = compositionLocalOf { false }

@Composable
fun AppTheme(
    darkTheme: Boolean = resolveDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor by ThemeManager.dynamicColorEnabled.collectAsState()
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColor
        else -> LightColor
    }
    val snackbarController = rememberAppSnackbarController()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = view.context as? Activity ?: return@SideEffect
            val window = activity.window
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = false  // همیشه متن روشن در نوار وضعیت
                isAppearanceLightNavigationBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme,
        LocalAppSnackbar provides snackbarController
    ) {
        MaterialTheme(
            colorScheme = colorScheme
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AppSnackbarBridge(controller = snackbarController)
                content()
                AppSnackbarHost(hostState = snackbarController.hostState)
            }
        }
    }
}
