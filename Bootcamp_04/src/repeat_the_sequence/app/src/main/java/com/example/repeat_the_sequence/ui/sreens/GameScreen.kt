package com.example.repeat_the_sequence.ui.sreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont
import com.example.repeat_the_sequence.view_model.SimonGameViewModel

class Game(private val context: Context,private val isFreeGame: Boolean) {
  private val gameViewModel = SimonGameViewModel()

  @Composable
  fun RenderScreen() {
    val lvl = remember {
      mutableStateOf(1)
    }

    Column(
      Modifier
        .fillMaxSize()
        .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Spacer(modifier = Modifier.size(50.dp))
      GameInfo("level", lvl)
      GameInfo("record", lvl)
      Spacer(modifier = Modifier.size(80.dp))
      Row {
        SoundButton("sound_1", "\uD83D\uDC37", lvl)
        SoundButton("sound_2", "\uD83D\uDC38", lvl)
      }
      Row {
        SoundButton("sound_3", "\uD83D\uDC3B", lvl)
        SoundButton("sound_4", "\uD83D\uDC2E", lvl)
      }
      Spacer(modifier = Modifier.weight(1f))
      PlayButton(gameViewModel, lvl)
    }
  }

  @Composable
  fun GameInfo(info: String, lvl: MutableState<Int>) {
    Box(
      modifier = Modifier
        .padding(5.dp)
        .width(300.dp)
        .height(80.dp)
    ) {
      Image(
        painter = painterResource(id = R.drawable.game_info),
        contentDescription = "${info}_info",
        modifier = Modifier.fillMaxSize()
      )
      Text(
        text = "${info.uppercase()}: ${lvl.value}",
        color = Color.White,
        fontSize = 30.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .align(Alignment.Center)
      )
    }
  }


  @Composable
  fun SoundButton(description: String, emoji: String, lvl: MutableState<Int>) {
    Box(
      modifier = Modifier
        .padding(5.dp)
        .width(110.dp)
        .height(110.dp)
        .clickable {
          gameViewModel.addPlayerSequence(description, lvl,context)
        }
    ) {
      Image(
        painter = painterResource(id = R.drawable.game_button),
        contentDescription = description,
        modifier = Modifier.fillMaxSize()
      )
      Text(
        text = emoji,
        fontSize = 35.sp,
        modifier = Modifier
          .align(Alignment.Center)
      )
    }
  }

  @Composable
  fun PlayButton(test: SimonGameViewModel, lvl: MutableState<Int>) {
    val textButton = if (lvl.value == 1) "PLAY" else "NEXT LEVEL"
    Box(
      modifier = Modifier
        .padding(vertical = 20.dp)
        .width(272.dp)
        .height(60.dp)
        .clickable {
          test.startGame(context, lvl)
        }
    ) {
      Image(
        painter = painterResource(id = R.drawable.button),
        contentDescription = "ButtonPlay",
        modifier = Modifier.fillMaxSize()
      )
      Text(
        text = textButton,
        color = Color.White,
        fontSize = 48.sp,
        fontFamily = stardewValleyFont,
        fontWeight = FontWeight.Normal,
        modifier = Modifier
          .align(Alignment.Center)
      )
    }
  }
}

