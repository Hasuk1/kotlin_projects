package com.example.something_calc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.core.utility.AppScreens
import com.example.feature.circles_2.ui.CirclesScreen
import com.example.feature.speech.ui.SpeechScreen
import com.example.prime_numbers.ui.PrimeScreen
import com.example.something_calc.ui.SomethingCalcApp
import com.example.thermohydrometer.ui.ThermohydrometerScreen

@Composable
fun SomethingCalcNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AppScreens.MENU.route) {
      SomethingCalcApp(navController)
    }
    composable(AppScreens.CIRCLE.route) {
      CirclesScreen {
        navController.navigateUp()
      }
    }
    composable(AppScreens.PRIMENUMBERS.route) {
      PrimeScreen {
        navController.navigateUp()
      }
    }
    composable(AppScreens.THERMOHYDROMETER.route) {
      ThermohydrometerScreen {
        navController.navigateUp()
      }
    }
    composable(AppScreens.SPEECH.route) {
      SpeechScreen {
        navController.navigateUp()
      }
    }
  }
}