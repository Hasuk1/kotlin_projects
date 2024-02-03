package com.example.repeat_the_sequence.view_model

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.repeat_the_sequence.R

class SimonGameViewModel : ViewModel() {
  private val sounds = arrayOf(
    Pair("sound_1", R.raw.sound_1),
    Pair("sound_2", R.raw.sound_2),
    Pair("sound_3", R.raw.sound_3),
    Pair("sound_4", R.raw.sound_4)
  )
  private val handler = Handler(Looper.getMainLooper())
  private val sequence = mutableListOf<Pair<String, Int>>()
  private var playerSequence = mutableListOf<Pair<String, Int>>()

  fun startGame(context: Context, lvl: MutableState<Int>) {
    playerSequence.clear()
    if (lvl.value == 1) sequence.clear()
    sequence.add(sounds[(Math.random() * 4).toInt()])
    for (i in 0 until lvl.value) {
      playSoundDelayed(context, i, (2000 * i).toLong())
    }
    Log.d("MyLog", "============================")
  }

  fun addPlayerSequence(soundName: String, lvl: MutableState<Int>,context: Context) {
    val soundId = when (soundName) {
      "sound_1" -> Pair(soundName, R.raw.sound_1)
      "sound_2" -> Pair(soundName, R.raw.sound_2)
      "sound_3" -> Pair(soundName, R.raw.sound_3)
      "sound_4" -> Pair(soundName, R.raw.sound_4)
      else -> Pair(soundName, R.raw.sound_1)
    }
    playerSequence.add(soundId)
    playSound(context,soundId.second)
    if (playerSequence.size == sequence.size) checkResult(lvl)
  }

  private fun checkResult(lvl: MutableState<Int>) {
    if (playerSequence == sequence) lvl.value++
  }

  private fun playSoundDelayed(context: Context, index: Int, delayMillis: Long) {
    Log.d("MyLog", "soundName: ${sequence[index].first}")
    handler.postDelayed({
      playSound(context, sequence[index].second)
    }, delayMillis)
  }

  private fun playSound(context: Context, sound: Int) {
    val mediaPlayer = MediaPlayer.create(context, sound)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener { mp ->
      mp.release()
    }
  }

  private fun playSequence() {
//    generateSequence()
//    for (sound in sequence){
//      playSound(sound)
//      Thread.sleep(1000)
//    }
  }

  private fun resetGame() {
    // Обработка ошибки или перезапуск игры
//    level = 1
//    generateSequence()
//    playSequence()
//    playerSequence.clear()
  }

  private fun onButtonClick(sound: String) {
//    playSound(sound)
//    playerSequence.add(sound)
//
//    if (!checkSequence()) {
    // Игрок сделал ошибку, обработайте это соответственно
//      resetGame()
//    } else if (playerSequence.size == sequence.size) {
    // Игрок правильно повторил последовательность, переходите к следующему уровню
//      level++
//      generateSequence()
//      playSequence()
//      playerSequence.clear()
//    }
  }


  private fun playSound(sound: String) {
//    val soundId = soundMap[sound] ?: return
//    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
  }

//  private fun checkSequence(): Boolean {
//    return sequence == playerSequence
//  }


}
