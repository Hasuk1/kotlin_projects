package com.example.smartcalculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.example.smartcalculator.enums.AppScreens

@Composable
fun MenuScreen(navController: NavHostController) {
  Column(
    Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.primary),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Button(modifier = Modifier
      .fillMaxWidth(0.7f)
      .fillMaxHeight(0.1f),
      colors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
      ),
      elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp, pressedElevation = 0.dp),
      onClick = {
        navController.navigate(
          AppScreens.CIRCLE.route,
          navOptions = NavOptions.Builder().setLaunchSingleTop(true).build()
        )
      }) {
      Text(text = "Circle", fontSize = 30.sp)
    }
  }
}