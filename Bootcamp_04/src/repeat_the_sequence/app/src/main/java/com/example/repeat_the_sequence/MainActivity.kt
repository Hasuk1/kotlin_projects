package com.example.repeat_the_sequence

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BackgroundImage()
      val navController = rememberNavController()
      NavHost(navController = navController, startDestination = Screen.MENU.name) {
        composable(Screen.MENU.name) {
          MenuScreen(navController)
        }
        composable("GameScreen") {
          GameScreen(this@MainActivity)
        }
      }
    }
  }
}

@Composable
fun BackgroundImage() {
  Image(
    painter = painterResource(id = R.drawable.menu_background),
    contentDescription = "menu_background",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxSize()
      .blur(2.dp, 2.dp)
      .alpha(0.85F)
  )
}
