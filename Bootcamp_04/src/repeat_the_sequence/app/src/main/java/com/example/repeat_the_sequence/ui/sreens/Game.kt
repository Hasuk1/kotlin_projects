package com.example.repeat_the_sequence.ui.sreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.enums.Screen
import com.example.repeat_the_sequence.ui.buttons.BackArrow
import com.example.repeat_the_sequence.ui.buttons.OptionButton
import com.example.repeat_the_sequence.ui.buttons.SoundButton
import com.example.repeat_the_sequence.ui.elements.GameInfo
import com.example.repeat_the_sequence.ui.elements.GameLogo
import com.example.repeat_the_sequence.ui.elements.InvitationText
import com.example.repeat_the_sequence.ui.elements.LoseInfo
import com.example.repeat_the_sequence.ui.theme.BackgroundImage
import com.example.repeat_the_sequence.ui.theme.DarkGreen
import com.example.repeat_the_sequence.ui.theme.LightGreen
import com.example.repeat_the_sequence.ui.theme.stardewValleyFont
import com.example.repeat_the_sequence.view_model.SimonGameVM
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game(
  context: Context, private val navController: NavController
) {
  private val vm = SimonGameVM(navController, context)
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
    val isSoundButtonBlocked = remember { mutableStateOf(!isFreeGame) }
    val coroutineScope = rememberCoroutineScope()
    currentLevel = lvl.value
    currentRecord = record.value

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
            vm.endGame()
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
      InvitationText(lvl, invitation, playButtonText, isSoundButtonBlocked)
      Row {
        SoundButton("sound_1", "\uD83D\uDC37", isSoundButtonBlocked) {
          vm.addPlayerSequence(isFreeGame, "sound_1", status, lvl, record)
        }
        SoundButton("sound_2", "\uD83D\uDC38", isSoundButtonBlocked) {
          vm.addPlayerSequence(isFreeGame, "sound_2", status, lvl, record)
        }
      }
      Row {
        SoundButton("sound_3", "\uD83D\uDC3B", isSoundButtonBlocked) {
          vm.addPlayerSequence(isFreeGame, "sound_3", status, lvl, record)
        }
        SoundButton("sound_4", "\uD83D\uDC2E", isSoundButtonBlocked) {
          vm.addPlayerSequence(isFreeGame, "sound_4", status, lvl, record)
        }
      }
      Spacer(modifier = Modifier.weight(1f))
      if (!isFreeGame) OptionButton(playButtonText.value) {
        isSoundButtonBlocked.value = true
        invitation.value = "Listen carefully"
        vm.startGame(lvl)
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
  }
}

@Composable
fun RenderGameSettingsScreen() {
  BackgroundImage()
  Row(
    modifier = Modifier
      .padding(10.dp)
      .fillMaxWidth()
  ) {
    BackArrow(description = "test") {

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