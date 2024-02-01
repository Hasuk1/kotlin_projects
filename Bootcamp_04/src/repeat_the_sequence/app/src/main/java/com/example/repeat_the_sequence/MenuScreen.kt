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
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont

@Composable
fun MenuScreen(onClick: (Screen) -> Unit) {
  BackgroundImage()
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    GameLogo()
    MenuButton("button_basic_game", "PLAY") { onClick(Screen.GAME) }
    MenuButton("button_free_game", "FREE GAME") { onClick(Screen.FREEGAME) }
    MenuButton("button_settings", "SETTINGS") { onClick(Screen.SETTINGS) }
    MenuButton("button_about", "ABOUT") { onClick(Screen.ABOUT) }
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
fun MenuButton(description: String, buttonText: String, onClick: () -> Unit) {
  Box(
    modifier = Modifier
      .padding(vertical = 20.dp)
      .width(272.dp)
      .height(60.dp)
      .clickable {
        onClick()
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
