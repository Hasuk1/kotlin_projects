package com.example.repeat_the_sequence.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.ui.components.buttons.BackArrowButton
import com.example.repeat_the_sequence.ui.components.images.BackgroundImage
import com.example.repeat_the_sequence.ui.theme.DarkGreen
import com.example.repeat_the_sequence.ui.theme.LightGreen
import com.example.repeat_the_sequence.ui.types.stardewValleyFont
/*
@Composable
fun RenderLoseScreen() {
  Column(
    Modifier
      .padding(10.dp)
      .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(Modifier.fillMaxSize(0.2F))
    LoseInfo(currentLevel, currentRecord)
    Spacer(Modifier.size(20.dp))
    OptionButton("try again") {
      navController.navigate(Screen.GAME.name) {
        popUpTo(Screen.LOSE.name) { inclusive = true }
        currentLevel = 1
      }
    }
    OptionButton("menu") {
      navController.navigate(Screen.MENU.name) {
        popUpTo(Screen.LOSE.name) { inclusive = true }
        currentLevel = 1
      }
    }
  }
}*/

@Composable
fun RenderGameSettingsScreen() {
  BackgroundImage()
  Row(
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth()
  ) {
    BackArrowButton(description = "test") {

    }
  }
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    ButtonBacklightSettings("Button backlight")
    SoundSettings("Sound")
    SoundDelaySettings("Delay")
    SoundListSettings("Sound theme")
  }
}

@Composable
fun SoundSettings(info: String) {
  var checked by remember { mutableStateOf(true) }
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(350.dp)
      .height(90.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_info),
      contentDescription = "${info}_info",
      modifier = Modifier.fillMaxSize()
    )
    Row(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = info,
        color = Color.White,
        fontSize = 30.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .weight(1f)
      )
      var colorBack by remember { mutableStateOf(LightGreen) }
      Card(
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .size(width = 45.dp, height = 25.dp),
        backgroundColor = colorBack,
        shape = RoundedCornerShape(10.dp)
      ) {
        Switch(
          checked = checked, onCheckedChange = {
            checked = it
            colorBack = if (checked) {
              LightGreen
            } else {
              Color.White
            }
          }, colors = SwitchDefaults.colors(
            uncheckedTrackAlpha = 1f,
            uncheckedThumbColor = LightGreen,
            uncheckedTrackColor = Color.White,
            checkedTrackAlpha = 1f,
            checkedThumbColor = DarkGreen,
            checkedTrackColor = LightGreen
          )
        )
      }
    }
  }
}

@Composable
fun ButtonBacklightSettings(info: String) {
  var checked by remember { mutableStateOf(true) }
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(350.dp)
      .height(90.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_info),
      contentDescription = "${info}_info",
      modifier = Modifier.fillMaxSize()
    )
    Row(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = info,
        color = Color.White,
        fontSize = 30.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .weight(1f)
      )
      var colorBack by remember { mutableStateOf(LightGreen) }
      Card(
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .size(width = 45.dp, height = 25.dp),
        backgroundColor = colorBack,
        shape = RoundedCornerShape(10.dp)
      ) {
        Switch(
          checked = checked, onCheckedChange = {
            checked = it
            colorBack = if (checked) {
              LightGreen
            } else {
              Color.White
            }
          }, colors = SwitchDefaults.colors(
            uncheckedTrackAlpha = 1f,
            uncheckedThumbColor = LightGreen,
            uncheckedTrackColor = Color.White,
            checkedTrackAlpha = 1f,
            checkedThumbColor = DarkGreen,
            checkedTrackColor = LightGreen
          )
        )
      }
    }
  }
}

@Composable
fun SoundDelaySettings(info: String) {
  var sliderPosition by remember { mutableStateOf(0) }
  Box(
    modifier = Modifier
      .padding(5.dp)
      .width(350.dp)
      .height(90.dp)
  ) {
    Image(
      painter = painterResource(id = R.drawable.game_info),
      contentDescription = "${info}_info",
      modifier = Modifier.fillMaxSize()
    )
    Row(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxSize(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "$info: ${sliderPosition.toDouble() / 10} s",
        color = Color.White,
        fontSize = 30.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .weight(1f)
      )

      Box(
        modifier = Modifier
          .padding(horizontal = 5.dp)
          .size(width = 165.dp, height = 25.dp)
      ) {
        Slider(
          value = sliderPosition.toFloat(),
          onValueChange = { sliderPosition = it.toInt() },
          colors = SliderDefaults.colors(
            thumbColor = DarkGreen, activeTrackColor = LightGreen
          ),
          steps = 8,
          valueRange = 1f..10f
        )
      }
    }
  }
}

@Composable
fun SoundListSettings(info: String) {
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