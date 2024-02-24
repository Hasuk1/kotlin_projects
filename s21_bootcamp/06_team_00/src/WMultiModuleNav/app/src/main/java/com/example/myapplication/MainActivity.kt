package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mylibrary.ThirdScreen

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      AppNavHost(navController, "third")
    }
  }
}

//
@Composable
fun AppNavHost(navController: NavHostController, startRoute: String) {
  NavHost(navController = navController, startDestination = startRoute) {
    composable("first") { FirstScreen("first", navController) }
    composable("second") { SecondScreen("second", navController) }
    composable("third") { ThirdScreen("third", navController) }
  }
}

@Composable
fun FirstScreen(text: String, navController: NavHostController) {
  Column {
    Text(
      text = text, fontSize = 50.sp
    )
    Button(onClick = { navController.navigate("second") }) {
      Text(
        text = "Go next module", fontSize = 50.sp
      )
    }
  }
}

@Composable
fun SecondScreen(text: String, navController: NavHostController) {
  Column {
    Text(
      text = text, fontSize = 50.sp
    )
    Button(onClick = { navController.navigate("third") }) {
      Text(
        text = "Go next module", fontSize = 50.sp
      )
    }
  }
}