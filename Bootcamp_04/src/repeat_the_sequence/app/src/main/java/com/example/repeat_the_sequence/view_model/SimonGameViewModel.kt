package com.example.repeat_the_sequence.view_model

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.repeat_the_sequence.R
import com.example.repeat_the_sequence.enums.GameState
import com.example.repeat_the_sequence.enums.Screen

class SimonGameViewModel(private val navController: NavController) : ViewModel() {
  private val sounds = arrayOf(
    Pair("sound_1", R.raw.sound_1),
    Pair("sound_2", R.raw.sound_2),
    Pair("sound_3", R.raw.sound_3),
    Pair("sound_4", R.raw.sound_4)
  )
  private val handler = Handler(Looper.getMainLooper())
  private val sequence = mutableListOf<Pair<String, Int>>()
  private val emojiSequence = mutableListOf<MutableState<TextUnit>>()
  private var playerSequence = mutableListOf<Pair<String, Int>>()

  fun startGame(
    context: Context,
    lvl: MutableState<Int>,
    listEmojiSize: MutableList<MutableState<TextUnit>>
  ) {
    playerSequence.clear()
    if (lvl.value == 1) sequence.clear()
    val randomIndex = (Math.random() * 4).toInt()
    sequence.add(sounds[randomIndex])
    emojiSequence.add(listEmojiSize[randomIndex])
    for (i in 0 until lvl.value) {
      playSoundDelayed(context, i, (2000 * i).toLong())
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
    record: MutableState<Int>,
    context: Context
  ) {
    val soundId = when (soundName) {
      "sound_1" -> Pair(soundName, R.raw.sound_1)
      "sound_2" -> Pair(soundName, R.raw.sound_2)
      "sound_3" -> Pair(soundName, R.raw.sound_3)
      "sound_4" -> Pair(soundName, R.raw.sound_4)
      else -> Pair(soundName, R.raw.sound_1)
    }
    playSound(context, soundId.second)
    if (!isFreeGame) {
      playerSequence.add(soundId)
      checkResult(status, lvl, record, context)
    }
  }

  private fun checkResult(
    status: MutableState<GameState>,
    lvl: MutableState<Int>,
    record: MutableState<Int>,
    context: Context
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
      if (lvl.value > record.value) {
        record.value = lvl.value
        context.getSharedPreferences("record", Context.MODE_PRIVATE).edit()
          .putInt("record", record.value).apply()
      }
    } else if (!result) {
      status.value = GameState.LOSE
      navController.navigate(Screen.LOSE.name) { popUpTo(Screen.GAME.name) { inclusive = true } }
    }
  }

  private fun playSoundDelayed(
    context: Context,
    index: Int,
    delayMillis: Long
  ) {
    Log.d("MyLog", "soundName: ${sequence[index].first}")
    emojiSequence[index].value = 50.sp
    handler.postDelayed({
      playSound(context, sequence[index].second)
      emojiSequence[index].value = 35.sp
    }, delayMillis)
  }

  private fun playSound(context: Context, sound: Int) {
    val mediaPlayer = MediaPlayer.create(context, sound)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener { mp ->
      mp.release()
    }
  }
}
