package com.example.repeat_the_sequence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.repeat_the_sequence.enums.Screen
import com.example.repeat_the_sequence.ui.sreens.Game
import com.example.repeat_the_sequence.ui.sreens.MenuScreen
import com.example.repeat_the_sequence.ui.theme.BackgroundImage

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BackgroundImage()
      val navController = rememberNavController()
      val game = Game(this@MainActivity, navController)
      NavHost(navController = navController, startDestination = Screen.MENU.name) {
        composable(Screen.MENU.name) {
          MenuScreen(navController)
        }
        composable(Screen.GAME.name) {
          game.RenderGameScreen(isFreeGame = false)
        }
        composable(Screen.LOSE.name) {
          game.RenderLoseScreen()
        }
        composable(Screen.FREEGAME.name){
          game.RenderGameScreen(isFreeGame = true)
        }
      }
    }
  }
}
