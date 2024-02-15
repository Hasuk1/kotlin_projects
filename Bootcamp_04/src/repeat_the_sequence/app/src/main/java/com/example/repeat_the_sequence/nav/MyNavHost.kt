package com.example.repeat_the_sequence.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.repeat_the_sequence.enums.AppScreens
import com.example.repeat_the_sequence.ui.screens.GameScreen
import com.example.repeat_the_sequence.ui.screens.MenuScreen
import com.example.repeat_the_sequence.viewmodel.SimonGameVM

@Composable
fun MyNavHost(navController: NavHostController, startRoute: String,vm: SimonGameVM){
  NavHost(navController = navController, startDestination = startRoute) {
    composable(AppScreens.MENU.route) {
      MenuScreen(navController)
    }
    composable(AppScreens.GAME.route) {
      GameScreen(
        isFreeGame = vm.gameMode(isFreeGame = false),
        viewModel = vm
      )
    }
    composable(AppScreens.FREEGAME.route) {
      GameScreen(
        isFreeGame = vm.gameMode(isFreeGame = true),
        viewModel = vm
      )
    }
//    composable(AppScreens.LOSE.route) {
//      LoseScreen()
//    }
//    composable(AppScreens.SETTINGS.route) {
//      GameSettingsScreen()
//    }
//    composable(AppScreens.ABOUT.route) {
//      AboutAppScreen()
//    }
  }
}