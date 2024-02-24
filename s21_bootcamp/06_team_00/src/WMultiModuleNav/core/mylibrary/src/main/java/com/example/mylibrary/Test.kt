package com.example.mylibrary

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ThirdScreen(text:String,navController: NavHostController) {
  Column {
    Text(
      text = text, fontSize = 50.sp
    )
    Button(onClick = { navController.navigate("first") }) {
      Text(
        text = "Go next module", fontSize = 50.sp
      )
    }
  }
}