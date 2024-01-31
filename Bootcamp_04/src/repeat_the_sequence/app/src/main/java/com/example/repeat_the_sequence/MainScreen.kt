package com.example.repeat_the_sequence

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun MainScreen() {
  Image(
    painter = painterResource(id = R.drawable.menu_background),
    contentDescription = "menu_background",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxSize()
      .blur(2.dp, 2.dp)
      .alpha(0.85F)
  )
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.size(40.dp))
    Image(
      painter = painterResource(id = R.drawable.game_logo),
      contentDescription = "game_logo",
      modifier = Modifier
        .width(390.dp)
        .height(220.dp)
    )
    Box(
      modifier = Modifier
        .width(300.dp)
        .height(70.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.button),
        contentDescription = "button_basic_game",
        modifier = Modifier.fillMaxSize()
      )
      Text(
        text = "NEW GAME",
        color = Color.White,
        fontSize = 35.sp,
        fontFamily = FontFamily.Cursive,
        modifier = Modifier
          .align(Alignment.Center)
      )
    }
    Spacer(modifier = Modifier.size(20.dp))
    Image(
      painter = painterResource(id = R.drawable.button),
      contentDescription = "button_free_game",
      modifier = Modifier
        .width(300.dp)
        .height(70.dp)
    )
    Spacer(modifier = Modifier.size(20.dp))
    Image(
      painter = painterResource(id = R.drawable.button),
      contentDescription = "button_settings",
      modifier = Modifier
        .width(300.dp)
        .height(70.dp)
    )
    Spacer(modifier = Modifier.size(20.dp))
    Image(
      painter = painterResource(id = R.drawable.button),
      contentDescription = "button_about",
      modifier = Modifier
        .width(300.dp)
        .height(70.dp)
    )
  }
}

