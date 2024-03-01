package com.example.core.designsystem.theme

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
import androidx.core.view.ViewCompat

private val LightColorScheme = lightColorScheme(
  primary = LightGray,
  inversePrimary = DarkBlue,
  onPrimary = Color.Black,
  primaryContainer = Color.White,
  onPrimaryContainer = Color.Black,
  secondary = CaribbeanGreen,
  onSecondary = Color.Black,
  tertiary = PurpleEggplant,
  onTertiary = Color.White,
  surface = LightBlue,
  error = Golden
)

private val DarkColorScheme = darkColorScheme(
  primary = DarkBlue,
  inversePrimary = LightGray,
  onPrimary = Color.White,
  primaryContainer = DarkLightBlue,
  onPrimaryContainer = Color.White,
  secondary = CaribbeanGreen,
  onSecondary = Color.Black,
  tertiary = PurpleEggplant,
  onTertiary = Color.Black,
  surface = RoyalBlue,
  error = Golden,
)

@Composable
fun SomethingCalcTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
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
      (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
      ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme, typography = Typography, content = content
  )
}
