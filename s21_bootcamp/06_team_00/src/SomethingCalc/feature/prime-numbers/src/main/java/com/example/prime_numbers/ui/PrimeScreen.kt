package com.example.prime_numbers.ui

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
import com.example.core.ui.components.VariantCheckbox
import com.example.prime_numbers.data.getNumberPrimeInfo

@Composable
fun PrimeScreen(goBack: () -> Unit) {
  val higherCheckedState = remember { mutableStateOf(true) }
  val lowerCheckedState = remember { mutableStateOf(!higherCheckedState.value) }
  val number = remember { mutableStateOf(TextFieldValue()) }
  var numberPrimeInfo by remember { mutableStateOf("") }

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
      Column(
        modifier = Modifier
          .padding(15.dp)
          .fillMaxWidth(0.9f)
          .height(140.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ), verticalArrangement = Arrangement.Center
      ) {
        VariantCheckbox("Higher", higherCheckedState) {
          if (!higherCheckedState.value) {
            higherCheckedState.value = !higherCheckedState.value
            lowerCheckedState.value = !higherCheckedState.value
          }
        }
        VariantCheckbox("Lower", lowerCheckedState) {
          if (!lowerCheckedState.value) {
            lowerCheckedState.value = !lowerCheckedState.value
            higherCheckedState.value = !lowerCheckedState.value
          }
        }
      }
      InputNumberBox(number, "Enter a number", Regex("^[0-9]*[0-9]*\$"))
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
            text = numberPrimeInfo,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
          )
        }
      }
      CalculateButton("Calculate") {
        val intNumber = number.value.text.toIntOrNull()
        numberPrimeInfo = if (intNumber != null) {
          getNumberPrimeInfo(higherCheckedState.value, intNumber)
        } else {
          Logger.error("Incorrect input number | Module: Prime")
          "Error"
        }
      }
    }
  }
}
