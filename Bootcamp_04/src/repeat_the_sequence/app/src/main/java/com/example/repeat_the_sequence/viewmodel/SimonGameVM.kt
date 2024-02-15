package com.example.repeat_the_sequence.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.AppScreens
import com.example.repeat_the_sequence.enums.GameMode
import com.example.repeat_the_sequence.enums.GameState

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
  private val handler = Handler(Looper.getMainLooper())
  private val sequence = mutableListOf<Pair<String, Int>>()
  private var playerSequence = mutableListOf<Pair<String, Int>>()

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

  fun startGame() {
    playerSequence.clear()
    if (level.value == 1) sequence.clear()
    sequence.add(sounds[(Math.random() * 4).toInt()])
    for (i in 0 until level.value) {
      playSoundDelayed(i, (2000 * i).toLong())
    }
    Log.d("MyLog", "============================")
  }

  fun endGame() {
    playerSequence.clear()
    sequence.clear()
    navController.navigate(AppScreens.MENU.route) {
      popUpTo(AppScreens.MENU.route) {
        inclusive = true
      }
    }
  }

  fun addPlayerSequence(
    soundName: String,
    status: MutableState<GameState>,
  ) {
    val soundId = when (soundName) {
      "sound_1" -> Pair(soundName, R.raw.sound_1)
      "sound_2" -> Pair(soundName, R.raw.sound_2)
      "sound_3" -> Pair(soundName, R.raw.sound_3)
      "sound_4" -> Pair(soundName, R.raw.sound_4)
      else -> Pair(soundName, R.raw.sound_1)
    }
    playSound(soundId.second)
    if (gameMode == GameMode.DEFAULTGAME) {
      playerSequence.add(soundId)
      checkResult(status)
    }
  }

  private fun checkResult(
    status: MutableState<GameState>,
  ) {
    var result = true
    for (i in 0 until playerSequence.size) {
      if (playerSequence[i] == sequence[i]) {
        continue
      } else {
        result = false
      }
    }
    if (playerSequence.size == sequence.size && result) {
      status.value = GameState.WIN
      level.value++
      if (level.value > record.value + 1) {
        record.value = level.value - 1
        context.getSharedPreferences("record", Context.MODE_PRIVATE).edit().putInt("record", record.value)
          .apply()
      }
    } else if (!result) {
      status.value = GameState.LOSE
      navController.navigate(AppScreens.LOSE.route) {
        popUpTo(AppScreens.GAME.route) {
          inclusive = true
        }
      }
    }
  }

  private fun playSoundDelayed(
    index: Int, delayMillis: Long
  ) {
    Log.d("MyLog", "soundName: ${sequence[index].first}")
    handler.postDelayed({
      playSound(sequence[index].second)
    }, delayMillis)
  }

  private fun playSound(sound: Int) {
    val mediaPlayer = MediaPlayer.create(context, sound)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener { mp ->
      mp.release()
    }
  }
}
