package com.example.something_calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.core.designsystem.theme.SomethingCalcTheme
import com.example.core.utility.AppScreens
import com.example.something_calc.navigation.SomethingCalcNavHost

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      SomethingCalcTheme {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val navController = rememberNavController()
        SomethingCalcNavHost(navController, AppScreens.MENU.route)
      }
    }
  }
}

