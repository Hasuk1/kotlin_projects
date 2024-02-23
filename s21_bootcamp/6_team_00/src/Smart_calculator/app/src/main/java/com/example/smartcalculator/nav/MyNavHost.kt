package com.example.smartcalculator.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.circles.CircleScreen
import com.example.smartcalculator.enums.AppScreens
import com.example.smartcalculator.ui.screens.MenuScreen

@Composable
fun MyNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AppScreens.MENU.route) {
      MenuScreen(navController)
    }
    composable(AppScreens.CIRCLE.route) {
      CircleScreen(navController)
    }
    /*composable(AppScreens.PRIME.route) {
      PrimeScreen(navController)
    }
    composable(AppScreens.THERMOHYDROMETER.route) {
      ThermohydrometerScreen(navController)
    }*/
  }
}