package com.example.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuButton(text: String, goModule: () -> Unit) {
  Button(modifier = Modifier
    .fillMaxWidth(0.9f)
    .height(100.dp)
    .padding(15.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = MaterialTheme.colorScheme.secondary,
      contentColor = MaterialTheme.colorScheme.onSecondary
    ),
    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp, pressedElevation = 0.dp),
    shape = RoundedCornerShape(20.dp),
    onClick = { goModule.invoke() }) {
    Text(text = text, fontSize = 32.sp)
  }
}