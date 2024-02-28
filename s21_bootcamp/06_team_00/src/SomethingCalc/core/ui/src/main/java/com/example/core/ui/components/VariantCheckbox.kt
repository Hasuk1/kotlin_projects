package com.example.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VariantCheckbox(
  text: String, checkedState: MutableState<Boolean>, checkedChange: () -> Unit
) {
  Row(
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth()
      .height(60.dp),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = text,
      color = MaterialTheme.colorScheme.onPrimary,
      fontSize = 20.sp,
      modifier = Modifier.weight(1f)
    )
    Checkbox(
      checked = checkedState.value,
      onCheckedChange = { checkedChange.invoke() },
      colors = CheckboxDefaults.colors(
        checkedColor = MaterialTheme.colorScheme.secondary,
        checkmarkColor = MaterialTheme.colorScheme.onSecondary
      )
    )
  }
}