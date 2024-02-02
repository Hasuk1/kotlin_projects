package com.example.repeat_the_sequence

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont

@Composable
fun MenuScreen(navController: NavHostController) {
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    GameLogo()
    MenuButton("GameScreen", "PLAY",navController)
    MenuButton("FreeGameScreen", "FREE GAME",navController)
    MenuButton("Settings", "SETTINGS",navController)
    MenuButton("About", "ABOUT",navController)
  }
}

@Composable
private fun GameLogo() {
  Image(
    painter = painterResource(id = R.drawable.game_logo),
    contentDescription = "game_logo",
    modifier = Modifier
      .padding(vertical = 20.dp)
      .width(390.dp)
      .height(220.dp)
  )
}

@Composable
fun MenuButton(description: String, buttonText: String, navController: NavHostController) {
  Box(
    modifier = Modifier
      .padding(vertical = 20.dp)
      .width(272.dp)
      .height(60.dp)
      .clickable {
        navController.navigate(description)
      }
  ) {
    Image(
      painter = painterResource(id = R.drawable.button),
      contentDescription = description,
      modifier = Modifier.fillMaxSize()
    )
    Text(
      text = buttonText,
      color = Color.White,
      fontSize = 48.sp,
      fontFamily = stardewValleyFont,
      fontWeight = FontWeight.Normal,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}
