package com.example.smartcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.smartcalculator.enums.AppScreens
import com.example.smartcalculator.nav.MyNavHost
import com.example.smartcalculator.ui.theme.SmartCalculatorTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SmartCalculatorTheme {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val navController = rememberNavController()
        MyNavHost(navController, AppScreens.MENU.route)
      }
    }
  }
}