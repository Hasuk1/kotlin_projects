package com.example.repeat_the_sequence.ui.sreens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.enums.Screen
import com.example.repeat_the_sequence.ui.buttons.BackArrow
import com.example.repeat_the_sequence.ui.buttons.OptionButton
import com.example.repeat_the_sequence.ui.buttons.SoundButton
import com.example.repeat_the_sequence.ui.elements.GameInfo
import com.example.repeat_the_sequence.ui.elements.GameLogo
import com.example.repeat_the_sequence.ui.elements.LoseInfo
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont
import com.example.repeat_the_sequence.view_model.SimonGameViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val lvl = remember { mutableStateOf(1) }
    val record = rememberSaveable { mutableStateOf(savedRecord) }
    val invitation = remember { mutableStateOf("") }
    val playButtonText = remember { mutableStateOf("play") }
    val status = remember { mutableStateOf(GameState.DEFAULT) }
    val isSoundButtonBlocked = remember { mutableStateOf(true) }
    val emoji1Size = remember { mutableStateOf(35.sp) }
    val emoji2Size = remember { mutableStateOf(35.sp) }
    val emoji3Size = remember { mutableStateOf(35.sp) }
    val emoji4Size = remember { mutableStateOf(35.sp) }
    val listEmojiSize = mutableListOf(emoji1Size, emoji2Size, emoji3Size, emoji4Size)
    val coroutineScope = rememberCoroutineScope()
    currentLevel = lvl.value
    currentRecord = record.value
    LaunchedEffect(lvl.value) {
      if (lvl.value > 1) invitation.value = "That's right!"
    }
    LaunchedEffect(invitation.value) {
      when (invitation.value) {
        "That's right!" -> {
          playButtonText.value = "next level"
          isSoundButtonBlocked.value = true
        }
        "Listen carefully" -> playButtonText.value = "..."
        "It's your turn" -> playButtonText.value = "Repeat"
      }
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
            gameViewModel.endGame()
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
      Spacer(modifier = Modifier.size(20.dp))
      Text(
        text = invitation.value,
        color = Color.White,
        fontSize = 40.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier.shadow(8.dp, CircleShape, false)
      )
      Spacer(modifier = Modifier.size(20.dp))
      Row {
        SoundButton("sound_1", "\uD83D\uDC37", emoji1Size, isSoundButtonBlocked) {
          gameViewModel.addPlayerSequence(isFreeGame, "sound_1", status, lvl, record, context)
        }
        SoundButton("sound_2", "\uD83D\uDC38", emoji2Size, isSoundButtonBlocked) {
          gameViewModel.addPlayerSequence(isFreeGame, "sound_2", status, lvl, record, context)
        }
      }
      Row {
        SoundButton("sound_3", "\uD83D\uDC3B", emoji3Size, isSoundButtonBlocked) {
          gameViewModel.addPlayerSequence(isFreeGame, "sound_3", status, lvl, record, context)
        }
        SoundButton("sound_4", "\uD83D\uDC2E", emoji4Size, isSoundButtonBlocked) {
          gameViewModel.addPlayerSequence(isFreeGame, "sound_4", status, lvl, record, context)
        }
      }
      Spacer(modifier = Modifier.weight(1f))
      if (!isFreeGame) OptionButton(playButtonText.value) {
        isSoundButtonBlocked.value = true
        invitation.value = "Listen carefully"
        gameViewModel.startGame(context, lvl,listEmojiSize)
        coroutineScope.launch {
          delay(2000 * lvl.value.toLong())
          isSoundButtonBlocked.value = false
          invitation.value = "It's your turn"
        }
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
  }
}
