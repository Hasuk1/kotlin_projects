package com.example.repeat_the_sequence.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont

@Composable
fun InvitationText(
  lvl: MutableState<Int>,
  invitation: MutableState<String>,
  playButtonText: MutableState<String>,
  isSoundButtonBlocked: MutableState<Boolean>
) {
  LaunchedEffect(lvl.value) {
    if (lvl.value > 1) invitation.value = "That's right!"
  }
  LaunchedEffect(invitation.value) {
    when (invitation.value) {
      "That's right!" -> {
        playButtonText.value = "next level"
        isSoundButtonBlocked.value = true
      }
      "Listen carefully" -> playButtonText.value = "..."
      "It's your turn" -> playButtonText.value = "Repeat"
    }
  }
  Text(
    text = invitation.value,
    color = Color.White,
    fontSize = 40.sp,
    fontFamily = stardewValleyFont,
    fontWeight = FontWeight.Normal,
    modifier = Modifier
      .shadow(8.dp, CircleShape, false)
      .padding(vertical = 20.dp)
  )
}