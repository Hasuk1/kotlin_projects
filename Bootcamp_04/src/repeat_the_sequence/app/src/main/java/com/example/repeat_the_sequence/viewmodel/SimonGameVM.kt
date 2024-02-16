package com.example.repeat_the_sequence.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.AppScreens
import com.example.repeat_the_sequence.enums.ButtonState
import com.example.repeat_the_sequence.enums.GameMode

class SimonGameVM(
  private val navController: NavController,
  @SuppressLint("StaticFieldLeak") private val context: Context
) : ViewModel() {
  private val sounds = arrayOf(
    Pair("sound_1", R.raw.sound_1),
    Pair("sound_2", R.raw.sound_2),
    Pair("sound_3", R.raw.sound_3),
    Pair("sound_4", R.raw.sound_4)
  )
  var level = mutableStateOf(1)
  private var savedRecord =
    context.getSharedPreferences("record", Context.MODE_PRIVATE).getInt("record", 1)
  var record = mutableStateOf(savedRecord)
  private var gameMode = GameMode.DEFAULTGAME
  private val sequence = mutableListOf<Pair<String, Int>>()
  private var playerSequence = mutableListOf<Pair<String, Int>>()
  private val handler = Handler()

  fun getNavController(): NavController {
    return navController
  }

  fun gameMode(isFreeGame: Boolean): Boolean {
    gameMode = when (isFreeGame) {
      true -> GameMode.FREEGAME
      false -> GameMode.DEFAULTGAME
    }
    return isFreeGame
  }

  fun startGame(buttonState: MutableState<Pair<String, ButtonState>>) {
    playerSequence.clear()
    if (level.value == 1) sequence.clear()
    val sound = sounds[(Math.random() * 4).toInt()]
    sequence.add(sound)
    for (i in 0 until level.value) {
      playSound(sequence[i].second, i.toLong() * 1000, buttonState,sound.first)
      Log.d("MyLog", "soundName: ${sequence[i].first}")
    }
    Log.d("MyLog", "============================")
  }

  fun endGame() {
    playerSequence.clear()
    sequence.clear()
    level.value = 1
  }

  fun addPlayerSequence(soundName: String) {
    val soundId = when (soundName) {
      "sound_1" -> Pair(soundName, R.raw.sound_1)
      "sound_2" -> Pair(soundName, R.raw.sound_2)
      "sound_3" -> Pair(soundName, R.raw.sound_3)
      "sound_4" -> Pair(soundName, R.raw.sound_4)
      else -> Pair(soundName, R.raw.sound_1)
    }
    playSound(soundId.second, 0, mutableStateOf(Pair("", ButtonState.AVAILABLE)),soundName)
    if (gameMode == GameMode.DEFAULTGAME) {
      playerSequence.add(soundId)
      checkResult()
    }
  }

  private fun checkResult() {
    var result = true
    for (i in 0 until playerSequence.size) {
      if (playerSequence[i] == sequence[i]) {
        continue
      } else {
        result = false
      }
    }
    if (playerSequence.size == sequence.size && result) {
      level.value++
      if (level.value > record.value + 1) {
        record.value = level.value - 1
        context.getSharedPreferences("record", Context.MODE_PRIVATE).edit()
          .putInt("record", record.value).apply()
      }
    } else if (!result) {
      navController.navigate(AppScreens.LOSE.route) {
        popUpTo(AppScreens.GAME.route) {
          inclusive = true
        }
      }
    }
  }

  private fun playSound(
    sound: Int,
    timeMillis: Long,
    buttonState: MutableState<Pair<String, ButtonState>>,
    soundName: String
  ) {
    val mediaPlayer = MediaPlayer.create(context, sound)
      buttonState.value = Pair(soundName, ButtonState.GLOW)
    handler.postDelayed({
      mediaPlayer.start()
      mediaPlayer.setOnCompletionListener { mp ->
        mp.release()
        buttonState.value = Pair(soundName, ButtonState.BLOCKED)
      }
    }, timeMillis)
  }
}
