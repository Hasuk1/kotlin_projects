package com.example.something_calc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.core.ui.components.MenuButton
import com.example.core.utility.AppScreens

@Composable
fun SomethingCalcApp(navController: NavHostController) {
  Column(
    Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.primary),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    MenuButton(text = "Circles-2") {
      navController.navigate(
        AppScreens.CIRCLE.route, navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
      )
    }
    MenuButton(text = "Prime numbers") {
      navController.navigate(
        AppScreens.PRIMENUMBERS.route,
        navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
      )
    }
    MenuButton(text = "Thermohydrometer") {
      navController.navigate(
        AppScreens.THERMOHYDROMETER.route,
        navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
      )
    }
    MenuButton(text = "Speech module") {
      navController.navigate(
        AppScreens.SPEECH.route, navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
      )
    }
  }
}
