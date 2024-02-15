package com.example.repeat_the_sequence.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.enums.Screen

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
  private val handler = Handler(Looper.getMainLooper())
  private val sequence = mutableListOf<Pair<String, Int>>()
  private var playerSequence = mutableListOf<Pair<String, Int>>()

  fun startGame(lvl: MutableState<Int>) {
    playerSequence.clear()
    if (lvl.value == 1) sequence.clear()
    sequence.add(sounds[(Math.random() * 4).toInt()])
    for (i in 0 until lvl.value) {
      playSoundDelayed(i, (2000 * i).toLong())
    }
    Log.d("MyLog", "============================")
  }

  fun endGame() {
    playerSequence.clear()
    sequence.clear()
  }

  fun addPlayerSequence(
    isFreeGame: Boolean,
    soundName: String,
    status: MutableState<GameState>,
    lvl: MutableState<Int>,
    record: MutableState<Int>
  ) {
    val soundId = when (soundName) {
      "sound_1" -> Pair(soundName, R.raw.sound_1)
      "sound_2" -> Pair(soundName, R.raw.sound_2)
      "sound_3" -> Pair(soundName, R.raw.sound_3)
      "sound_4" -> Pair(soundName, R.raw.sound_4)
      else -> Pair(soundName, R.raw.sound_1)
    }
    playSound(soundId.second)
    if (!isFreeGame) {
      playerSequence.add(soundId)
      checkResult(status, lvl, record)
    }
  }

  private fun checkResult(
    status: MutableState<GameState>,
    lvl: MutableState<Int>,
    record: MutableState<Int>
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
      lvl.value++
      if (lvl.value > record.value + 1) {
        record.value = lvl.value - 1
        context.getSharedPreferences("record", Context.MODE_PRIVATE).edit()
          .putInt("record", record.value).apply()
      }
    } else if (!result) {
      status.value = GameState.LOSE
      navController.navigate(Screen.LOSE.name) { popUpTo(Screen.GAME.name) { inclusive = true } }
    }
  }

  private fun playSoundDelayed(
    index: Int,
    delayMillis: Long
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
