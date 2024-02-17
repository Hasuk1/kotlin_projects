package com.example.repeat_the_sequence.ui.components.combobox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.ui.types.stardewValleyFont

@Composable
fun SoundListBox(info: String) {
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(350.dp)
      .height(155.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.combo_box),
      contentDescription = "${info}_info",
      modifier = Modifier.fillMaxSize()
    )
    Row(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxHeight(0.33f)
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "$info: ",
        color = Color.White,
        fontSize = 30.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .weight(1f)
      )
    }
  }
}