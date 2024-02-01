package com.example.repeat_the_sequence

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont

@Preview(showBackground = true)
@Composable
private fun Preview() {
  GameScreen()

}

@Composable
fun GameScreen() {
  BackgroundImage()
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.size(50.dp))
      GameInfo("level")
      GameInfo("record")
    Spacer(modifier = Modifier.size(80.dp))
    Row() {
      SoundButton("pig_button", "\uD83D\uDC37")
      SoundButton("frog_button", "\uD83D\uDC38")
    }
    Row() {
      SoundButton("bear_button", "\uD83D\uDC3B")
      SoundButton("cow_button", "\uD83D\uDC2E")
    }
    Spacer(modifier = Modifier.weight(1f))
    MenuButton(description = "button_play", buttonText = "PLAY") {
    }
  }
}

@Composable
fun GameInfo(info: String) {
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(300.dp)
      .height(80.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_info),
      contentDescription = "${info}_info",
      modifier = Modifier.fillMaxSize()
    )
    Text(
      text = "${info.uppercase()}: 0",
      color = Color.White,
      fontSize = 30.sp,
      fontFamily = stardewValleyFont,
      fontWeight = FontWeight.Normal,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}

@Composable
fun SoundButton(description: String, emoji: String) {
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(110.dp)
      .height(110.dp)
      .clickable {

      }
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_button),
      contentDescription = description,
      modifier = Modifier.fillMaxSize()
    )
    Text(
      text = emoji,
      fontSize = 35.sp,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}
