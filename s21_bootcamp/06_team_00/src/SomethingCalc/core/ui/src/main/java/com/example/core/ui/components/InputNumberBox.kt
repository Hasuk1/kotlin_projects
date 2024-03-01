package com.example.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputNumberBox(number: MutableState<TextFieldValue>, placeholder: String, regex: Regex) {
  Row(
    modifier = Modifier
      .fillMaxWidth(0.9f)
      .height(110.dp)
      .padding(15.dp)
      .background(
        color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
      ),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
  ) {
    TextField(
      value = number.value,
      onValueChange = {
        if (it.text.matches(regex)) {
          number.value = it
        }
      },
      placeholder = {
        Text(
          placeholder,
          color = Color.Gray,
          fontSize = 26.sp,
          fontWeight = FontWeight.Bold,
          textAlign = TextAlign.Center
        )
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
      ),
      textStyle = TextStyle(
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      ),
      singleLine = true,
      maxLines = 1,
      shape = RoundedCornerShape(16.dp),
      colors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
      ),
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.9f)
        .padding(horizontal = 15.dp, vertical = 5.dp)
    )
  }
}