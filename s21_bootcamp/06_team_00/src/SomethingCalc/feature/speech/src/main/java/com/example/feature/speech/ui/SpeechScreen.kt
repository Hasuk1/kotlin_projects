package com.example.feature.speech.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.core.logger.Logger
import com.example.core.ui.components.CalculateButton
import com.example.core.ui.components.InputNumberBox
import com.example.feature.speech.data.convertNumberToText

@Composable
fun SpeechScreen(goBack: () -> Unit) {
  val number = remember { mutableStateOf(TextFieldValue()) }
  var numberToText by remember { mutableStateOf("") }
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
      InputNumberBox(number, "Enter a number", Regex("^-?[0-9]*[0-9]*\$"))
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth(0.9f)
          .height(250.dp)
          .padding(15.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        item {
          Text(
            modifier = Modifier.padding(10.dp),
            text = numberToText,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            softWrap = true,
            textAlign = TextAlign.Center
          )
        }
      }
      CalculateButton("Convert") {
        val intNumber = number.value.text.toIntOrNull()
        numberToText = if (intNumber != null) {
          convertNumberToText(intNumber)
        } else {
          Logger.error("Incorrect input number | Module: Speech")
          "Error"
        }
      }
    }
  }
}