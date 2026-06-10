package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
      primary = PrimaryPurple,
      secondary = PrimaryPink,
      tertiary = PrimaryPurple,
      background = Color(0xFF1E1E2C),
      surface = Color(0xFF2D2D44),
      onPrimary = Color.White,
      onBackground = Color.White,
      onSurface = Color.White
  )

private val LightColorScheme =
  lightColorScheme(
    primary = PrimaryPurple,
    secondary = PrimaryPink,
    tertiary = PrimaryPurple,
    background = FrostedBackground,
    surface = FrostedWhite60,
    onPrimary = Color.White,
    onBackground = TextBody,
    onSurface = TextBody
  )

@Composable
fun LovedOneTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // We disable dynamic color to preserve our custom Frosted Glass theme
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
