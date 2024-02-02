package com.example.repeat_the_sequence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      NavHost(navController = navController, startDestination = Screen.MENU.name) {
        composable(Screen.MENU.name) {
          MenuScreen(navController)
        }
        composable("GameScreen") {
          GameScreen()
        }
      }
    }
  }
}
