package com.example.repeat_the_sequence.ui.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.repeat_the_sequence.R

@Composable
fun BackArrow(description: String, onClick: () -> Unit) {
  Box(modifier = Modifier
    .width(33.dp)
    .height(30.dp)
    .clickable {
      onClick.invoke()
    }) {
    Image(
      painter = painterResource(id = R.drawable.back_arrow),
      contentDescription = description,
      modifier = Modifier.fillMaxSize()
    )
  }
}