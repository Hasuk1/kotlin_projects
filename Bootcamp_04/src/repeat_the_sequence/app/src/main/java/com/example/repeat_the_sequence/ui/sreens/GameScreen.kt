package com.example.repeat_the_sequence.ui.sreens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.enums.Screen
import com.example.repeat_the_sequence.ui.buttons.BackArrow
import com.example.repeat_the_sequence.ui.buttons.OptionButton
import com.example.repeat_the_sequence.ui.buttons.SoundButton
import com.example.repeat_the_sequence.ui.elements.GameInfo
import com.example.repeat_the_sequence.ui.elements.GameLogo
import com.example.repeat_the_sequence.ui.elements.LoseInfo
import com.example.repeat_the_sequence.view_model.SimonGameViewModel

class Game(
  private val context: Context,
  private val navController: NavController
) {
  private val gameViewModel = SimonGameViewModel(navController)
  private var currentLevel = 1
  private var currentRecord = 0
  private var savedRecord =
    context.getSharedPreferences("record", Context.MODE_PRIVATE).getInt("record", 1)

  @Composable
  fun RenderGameScreen(isFreeGame: Boolean) {
    val lvl = remember {
      mutableStateOf(currentLevel)
    }
    currentLevel = lvl.value
    val record = rememberSaveable {
      mutableStateOf(savedRecord)
    }
    currentRecord = record.value
    val status = remember {
      mutableStateOf(GameState.DEFAULT)
    }
    Column(
      Modifier
        .fillMaxSize()
        .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .height(30.dp),
        horizontalAlignment = Alignment.Start,
      ) {
        BackArrow(description = "back_arrow_menu") {
          navController.navigate(Screen.MENU.name) {
            popUpTo(Screen.GAME.name) { inclusive = true }
          }
        }
      }
      if (isFreeGame) {
        GameLogo()
      } else {
        GameInfo("level", lvl)
        GameInfo("record", record)
      }
      Spacer(modifier = Modifier.size(80.dp))
      Row {
        SoundButton("sound_1", "\uD83D\uDC37") {
          gameViewModel.addPlayerSequence("sound_1", status, lvl, record, context)
        }
        SoundButton("sound_2", "\uD83D\uDC38") {
          gameViewModel.addPlayerSequence("sound_2", status, lvl, record, context)
        }
      }
      Row {
        SoundButton("sound_3", "\uD83D\uDC3B") {
          gameViewModel.addPlayerSequence("sound_3", status, lvl, record, context)
        }
        SoundButton("sound_4", "\uD83D\uDC2E") {
          gameViewModel.addPlayerSequence("sound_4", status, lvl, record, context)
        }
      }
      Spacer(modifier = Modifier.weight(1f))
      if (!isFreeGame) OptionButton(if (lvl.value == 1) "play" else "next level") {
        gameViewModel.startGame(context, lvl)
      }
    }
  }

  @Composable
  fun RenderLoseScreen() {
    Column(
      Modifier
        .fillMaxSize()
        .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(Modifier.fillMaxSize(0.2F))
      LoseInfo(currentLevel, currentRecord)
      Spacer(Modifier.size(20.dp))
      OptionButton("try again") {
        navController.navigate(Screen.GAME.name) {
          popUpTo(Screen.LOSE.name) { inclusive = true }
        }
      }
      OptionButton("menu") {
        navController.navigate(Screen.MENU.name) {
          popUpTo(Screen.LOSE.name) { inclusive = true }
        }
      }
    }
    currentLevel = 1
  }
}
