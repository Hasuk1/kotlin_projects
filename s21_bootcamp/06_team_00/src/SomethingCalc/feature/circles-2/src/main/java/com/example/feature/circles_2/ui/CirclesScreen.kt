package com.example.feature.circles_2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.designsystem.icon.CalcIcons
import com.example.core.ui.components.CalculateButton
import com.example.core.ui.components.CircleTextField
import com.example.feature.circles_2.data.Circle
import com.example.feature.circles_2.data.getCirclesStatus

@Composable
fun CirclesScreen(goBack: () -> Unit) {
  var circlesStatus by remember { mutableStateOf("") }
  val inputStateX1 = remember { mutableStateOf(TextFieldValue()) }
  val inputStateY1 = remember { mutableStateOf(TextFieldValue()) }
  val inputStateR1 = remember { mutableStateOf(TextFieldValue()) }
  val inputStateX2 = remember { mutableStateOf(TextFieldValue()) }
  val inputStateY2 = remember { mutableStateOf(TextFieldValue()) }
  val inputStateR2 = remember { mutableStateOf(TextFieldValue()) }
  Column(
    Modifier
      .fillMaxSize()
      .background(color = MaterialTheme.colorScheme.primary)
      .padding(vertical = 35.dp),
  ) {
    IconButton(
      onClick = { goBack.invoke() }, colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.secondary
      )
    ) {
      Icon(CalcIcons.ArrowBack, contentDescription = "Go Back")
    }
    Column(
      Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
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
        CircleTextField(inputStateX1, "x1")
        CircleTextField(inputStateY1, "y1")
        CircleTextField(inputStateR1, "r1")
      }
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
        CircleTextField(inputStateX2, "x2")
        CircleTextField(inputStateY2, "y2")
        CircleTextField(inputStateR2, "r2")
      }
      Box(
        modifier = Modifier
          .fillMaxWidth(0.9f)
          .height(250.dp)
          .padding(15.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ), contentAlignment = Alignment.Center
      ) {
        Text(
          text = circlesStatus,
          color = MaterialTheme.colorScheme.onPrimary,
          fontSize = 20.sp,
          textAlign = TextAlign.Center
        )
      }
      CalculateButton("Calculate") {
        val x1 = inputStateX1.value.text.toDoubleOrNull() ?: 0.0
        val y1 = inputStateY1.value.text.toDoubleOrNull() ?: 0.0
        val r1 = inputStateR1.value.text.toDoubleOrNull() ?: 0.0
        val x2 = inputStateX2.value.text.toDoubleOrNull() ?: 0.0
        val y2 = inputStateY2.value.text.toDoubleOrNull() ?: 0.0
        val r2 = inputStateR2.value.text.toDoubleOrNull() ?: 0.0
        circlesStatus = getCirclesStatus(Circle(x1, y1, r1), Circle(x2, y2, r2))
      }
    }
  }
}