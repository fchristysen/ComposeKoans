package org.greenfroyo.composekoans.insightj

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import org.greenfroyo.composekoans.ui.theme.ColorDL

val Cream = Color(0xFFFDFCF8)

val textPrimary = ColorDL(
    Color(0xFFD2D2D2),
    Color(0xFF2D2D2D)
)
val textSecondary = ColorDL(
    Color(0xFF7E7972),
    Color(0xFF81868D),
)
val divider = Color(0xFFD8D6D2)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE6DDC4),
    secondary = Color(0xFF678983),
    tertiary = Color(0xFFF0E9D2),
    background = Color(0xFF393646),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF565657),
    secondary = Color(0xFFF4EEE0),
    tertiary = Color(0xFF696369),
    background = Color(0xFFF5F3F0),
)

fun typography(darkTheme: Boolean) = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = if(darkTheme) textPrimary.darkColor else textPrimary.lightColor,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = if(darkTheme) textSecondary.darkColor else textSecondary.lightColor,
        letterSpacing = 0.5.sp
    ),

)

@Composable
fun InsightJTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = typography(darkTheme),
    )
}