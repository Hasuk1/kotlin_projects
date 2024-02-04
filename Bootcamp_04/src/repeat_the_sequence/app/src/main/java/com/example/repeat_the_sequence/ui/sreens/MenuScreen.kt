package com.example.repeat_the_sequence.ui.sreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.repeat_the_sequence.enums.Screen
import com.example.repeat_the_sequence.ui.buttons.OptionButton
import com.example.repeat_the_sequence.ui.elements.GameLogo

@Composable
fun MenuScreen(navController: NavHostController) {
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    GameLogo()
    OptionButton("Play") {
      navController.navigate(Screen.GAME.name) {
        popUpTo(Screen.MENU.name) { inclusive = true }
      }
    }
    OptionButton("Free game") {
      navController.navigate(Screen.FREEGAME.name) {
        popUpTo(Screen.MENU.name) { inclusive = true }
      }
    }
    OptionButton("Settings") {
      navController.navigate(Screen.SETTINGS.name) {
        popUpTo(Screen.MENU.name) { inclusive = true }
      }
    }
    OptionButton("About") {
      navController.navigate(Screen.ABOUT.name) {
        popUpTo(Screen.MENU.name) { inclusive = true }
      }
    }
  }
}
