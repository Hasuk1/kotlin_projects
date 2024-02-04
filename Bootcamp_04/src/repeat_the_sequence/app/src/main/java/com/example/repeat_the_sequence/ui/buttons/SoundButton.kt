package com.example.repeat_the_sequence.ui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R

@Composable
fun SoundButton(description: String, emoji: String, onClick: () -> Unit) {
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(110.dp)
      .height(110.dp)
      .clickable {
        onClick.invoke()
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