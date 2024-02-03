package com.example.repeat_the_sequence.ui.sreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.BackgroundImage
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont
import com.example.repeat_the_sequence.view_model.SimonGameViewModel

@Preview(showBackground = true)
@Composable
fun LoseScreen() {
  BackgroundImage()
  Column(
    Modifier
      .fillMaxSize()
      .padding(10.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.fillMaxSize(0.2F))
    Box(
      modifier = Modifier
        .width(272.dp)
        .height(272.dp)
    ) {
      Image(
        painter = painterResource(R.drawable.lose_window),
        contentDescription = "lose_window",
        modifier = Modifier.fillMaxSize()
      )
      Column(
        Modifier
          .fillMaxSize()
          .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Column(
          Modifier.fillMaxHeight(0.54F)
        ) {
          Text(
            text = "YOU",
            color = Color.White,
            fontSize = 68.sp,
            fontFamily = stardewValleyFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(top = 3.dp)
          )
          Text(
            text = "LOSE",
            color = Color.White,
            fontSize = 68.sp,
            fontFamily = stardewValleyFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
        Text(
          text = "Result: 9",
          color = Color.White,
          fontSize = 40.sp,
          fontFamily = stardewValleyFont,
          fontWeight = FontWeight.Normal,
          modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(10.dp)
        )
        Text(
          text = "Record: 55",
          color = Color.White,
          fontSize = 40.sp,
          fontFamily = stardewValleyFont,
          fontWeight = FontWeight.Normal,
          modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(10.dp)
        )
      }
    }
    OptionButton("TRY AGAIN")
    OptionButton("MENU")
  }
}

@Composable
fun OptionButton(text:String) {
  Box(
    modifier = Modifier
      .padding(top = 20.dp)
      .width(272.dp)
      .height(60.dp)
      .clickable {

      }
  ) {
    Image(
      painter = painterResource(id = R.drawable.button),
      contentDescription = "ButtonPlay",
      modifier = Modifier.fillMaxSize()
    )
    Text(
      text = text,
      color = Color.White,
      fontSize = 48.sp,
      fontFamily = stardewValleyFont,
      fontWeight = FontWeight.Normal,
      modifier = Modifier
        .align(Alignment.Center)
    )
  }
}
