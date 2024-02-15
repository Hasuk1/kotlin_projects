package com.example.repeat_the_sequence.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.ui.components.buttons.BackArrowButton
import com.example.repeat_the_sequence.ui.components.buttons.RectangleButton
import com.example.repeat_the_sequence.ui.components.buttons.SquareButton
import com.example.repeat_the_sequence.ui.components.information.GameInfo
import com.example.repeat_the_sequence.ui.components.information.GameInvitation
import com.example.repeat_the_sequence.ui.elements.GameLogo
import com.example.repeat_the_sequence.viewmodel.SimonGameVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(isFreeGame: Boolean, viewModel: SimonGameVM) {
  val lvl = remember { viewModel.level }
  val record = remember { viewModel.record }
  val invitation = remember { mutableStateOf("") }
  val playButtonText = remember { mutableStateOf("play") }
  val status = remember { mutableStateOf(GameState.DEFAULT) }
  val isSoundButtonBlocked = remember { mutableStateOf(!isFreeGame) }
  val coroutineScope = rememberCoroutineScope()

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
      BackArrowButton(description = "back_arrow_menu") {
        viewModel.endGame()
      }
    }
    if (isFreeGame) {
      GameLogo()
    } else {
      GameInfo("level", lvl)
      GameInfo("record", record)
    }
    GameInvitation(lvl, invitation, playButtonText, isSoundButtonBlocked)
    Row {
      SquareButton("sound_1", "\uD83D\uDC37", isSoundButtonBlocked) {
        viewModel.addPlayerSequence("sound_1", status)
      }
      SquareButton("sound_2", "\uD83D\uDC38", isSoundButtonBlocked) {
        viewModel.addPlayerSequence("sound_2", status)
      }
    }
    Row {
      SquareButton("sound_3", "\uD83D\uDC3B", isSoundButtonBlocked) {
        viewModel.addPlayerSequence("sound_3", status)
      }
      SquareButton("sound_4", "\uD83D\uDC2E", isSoundButtonBlocked) {
        viewModel.addPlayerSequence("sound_4", status)
      }
    }
    Spacer(modifier = Modifier.weight(1f))
    if (!isFreeGame) RectangleButton(playButtonText.value) {
      isSoundButtonBlocked.value = true
      invitation.value = "Listen carefully"
      viewModel.startGame()
      coroutineScope.launch {
        delay(2000 * lvl.value.toLong())
        isSoundButtonBlocked.value = false
        invitation.value = "It's your turn"
      }
    }
  }
}