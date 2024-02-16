package com.example.repeat_the_sequence.ui.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.ButtonState

@Composable
fun SquareButton(
  description: String,
  emoji: String,
  buttonState: MutableState<Pair<String, ButtonState>>,
  onClick: () -> Unit
) {

  val contrast = 1f // 0f..10f (1 should be default)
  val brightness = 50f // -255f..255f (0 should be default)
  val colorMatrix = floatArrayOf(
    contrast, 0f, 0f, 0f, brightness,
    0f, contrast, 0f, 0f, brightness,
    0f, 0f, contrast, 0f, brightness,
    0f, 0f, 0f, 1f, 0f
  )

  val blockedColorFilter = ColorFilter.lighting(Color.DarkGray, Color.DarkGray)
  val lightingColorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix))
  val myColorFilter = when (buttonState.value.first) {
    "all" -> {
      when (buttonState.value.second) {
        ButtonState.BLOCKED -> blockedColorFilter
        ButtonState.AVAILABLE -> null
        ButtonState.GLOW -> lightingColorFilter
      }
    }
    else -> {
      if (buttonState.value.first == description) {
        lightingColorFilter
      } else {
        null
      }
    }
  }

  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(110.dp)
      .height(110.dp)
      .clickable {
        if (buttonState.value.second == ButtonState.AVAILABLE) onClick.invoke()
      }
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_button),
      contentDescription = description,
      modifier = Modifier.fillMaxSize(),
      colorFilter = myColorFilter
    )
    Text(
      text = emoji,
//      style = TextStyle(shadow = Shadow(Color.White, Offset(0f, 0f), 40f)),
      fontSize = 35.sp,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}