package com.example.thermohydrometer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.core.logger.Logger
import com.example.core.ui.components.CalculateButton
import com.example.core.ui.components.InputNumberBox
import com.example.core.ui.components.VariantCheckbox
import com.example.core.utility.Mode
import com.example.core.utility.Season
import com.example.thermohydrometer.data.getTemperatureInfo

@Composable
fun ThermohydrometerScreen(goBack: () -> Unit) {
  val winterCheckedState = remember { mutableStateOf(true) }
  val summerCheckedState = remember { mutableStateOf(!winterCheckedState.value) }
  val celsiusCheckedState = remember { mutableStateOf(true) }
  val kelvinCheckedState = remember { mutableStateOf(!celsiusCheckedState.value) }
  val fahrenheitCheckedState = remember { mutableStateOf(!celsiusCheckedState.value) }
  val temperature = remember { mutableStateOf(TextFieldValue()) }
  var temperatureInfo by remember { mutableStateOf("") }
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
    ) {
      Column(
        modifier = Modifier
          .padding(15.dp)
          .fillMaxWidth(0.9f)
          .height(240.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ), verticalArrangement = Arrangement.Center
      ) {
        VariantCheckbox("Celsius", celsiusCheckedState) {
          if (!celsiusCheckedState.value) {
            celsiusCheckedState.value = !celsiusCheckedState.value
            kelvinCheckedState.value = !celsiusCheckedState.value
            fahrenheitCheckedState.value = !celsiusCheckedState.value
          }
        }
        VariantCheckbox("Celsius", kelvinCheckedState) {
          if (!kelvinCheckedState.value) {
            kelvinCheckedState.value = !kelvinCheckedState.value
            celsiusCheckedState.value = !kelvinCheckedState.value
            fahrenheitCheckedState.value = !kelvinCheckedState.value
          }
        }
        VariantCheckbox("Fahrenheit", fahrenheitCheckedState) {
          if (!fahrenheitCheckedState.value) {
            fahrenheitCheckedState.value = !fahrenheitCheckedState.value
            celsiusCheckedState.value = !fahrenheitCheckedState.value
            kelvinCheckedState.value = !fahrenheitCheckedState.value
          }
        }
      }
      Column(
        modifier = Modifier
          .padding(15.dp)
          .fillMaxWidth(0.9f)
          .height(140.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ), verticalArrangement = Arrangement.Center
      ) {
        VariantCheckbox("Winter", winterCheckedState) {
          if (!winterCheckedState.value) {
            winterCheckedState.value = !winterCheckedState.value
            summerCheckedState.value = !winterCheckedState.value
          }
        }
        VariantCheckbox("Summer", summerCheckedState) {
          if (!summerCheckedState.value) {
            summerCheckedState.value = !summerCheckedState.value
            winterCheckedState.value = !summerCheckedState.value
          }
        }
      }
      InputNumberBox(temperature, "Temperature ˚C", Regex("^[-+]?[0-9]*\\.?[0-9]*$"))
      Box(
        modifier = Modifier
          .fillMaxWidth(0.9f)
          .height(140.dp)
          .padding(15.dp)
          .background(
            color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(20.dp)
          ), contentAlignment = Alignment.Center
      ) {
        Text(
          text = temperatureInfo,
          color = MaterialTheme.colorScheme.onPrimary,
          fontSize = 20.sp,
          textAlign = TextAlign.Center
        )
      }
      CalculateButton("Calculate") {
        val mode = when {
          kelvinCheckedState.value -> Mode.KELVIN
          celsiusCheckedState.value -> Mode.CELSIUS
          fahrenheitCheckedState.value -> Mode.FAHRENHEIT
          else -> Mode.CELSIUS
        }
        Logger.info("Entered output mode: ${mode.name} | Module: Thermometer")
        val season = if (winterCheckedState.value) Season.WINTER else Season.SUMMER
        Logger.info("Entered season: ${season.name} | Module: Thermometer")
        val temperatureFloat = temperature.value.text.toFloatOrNull()
        temperatureInfo = if (temperatureFloat != null) {
          getTemperatureInfo(mode, season, temperatureFloat)
        } else {
          Logger.error("Incorrect input temperature | Module: Thermometer")
          "Error"
        }
      }
    }
  }
}