package com.example.repeat_the_sequence.ui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R

@Composable
fun SoundButton(
  description: String,
  emoji: String,
  emojiSize:MutableState<TextUnit>,
  isBlocked: MutableState<Boolean> = mutableStateOf(true),
  onClick: () -> Unit
) {
  val blockedColorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(110.dp)
      .height(110.dp)
      .clickable {
        if (!isBlocked.value) onClick.invoke()
      }
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_button),
      contentDescription = description,
      modifier = Modifier.fillMaxSize(),
      colorFilter = if (isBlocked.value) blockedColorFilter else null
    )
    Text(
      text = emoji,
      fontSize = emojiSize.value,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}