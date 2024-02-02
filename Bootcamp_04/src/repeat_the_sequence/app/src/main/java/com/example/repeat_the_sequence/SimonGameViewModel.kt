package com.example.repeat_the_sequence

import android.media.SoundPool
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SimonGameViewModel  : ViewModel(){
  private val soundPool =SoundPool.Builder().setMaxStreams(1).build()
  private val soundMap = mapOf(
    "pig_button" to R.raw.sound_1,
    "frog_button" to R.raw.sound_2,
    "bear_button" to R.raw.sound_3,
    "cow_button" to R.raw.sound_4,
  )
  private val sequence = mutableListOf<String>()
  private var playerSequence = mutableListOf<String>()
  private var level = 1
  init {
    generateSequence()
  }

  fun playSequence(){
    generateSequence()
    for (sound in sequence){
      playSound(sound)
      Thread.sleep(1000)
    }
  }

  private fun resetGame() {
    // Обработка ошибки или перезапуск игры
    level = 1
    generateSequence()
    playSequence()
    playerSequence.clear()
  }

  fun onButtonClick(sound: String) {
    playSound(sound)
    playerSequence.add(sound)

    if (!checkSequence()) {
      // Игрок сделал ошибку, обработайте это соответственно
      resetGame()
    } else if (playerSequence.size == sequence.size) {
      // Игрок правильно повторил последовательность, переходите к следующему уровню
      level++
      generateSequence()
      playSequence()
      playerSequence.clear()
    }
  }

  private fun generateSequence() {
    sequence.clear()
    repeat(level) {
      val randomIndex = (0 until soundMap.size).random()
      sequence.add(soundMap.keys.toList()[randomIndex])
    }
  }

  private fun playSound(sound: String) {
    val soundId = soundMap[sound] ?: return
    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
  }

  private fun checkSequence(): Boolean {
    return sequence == playerSequence
  }

  override fun onCleared() {
    super.onCleared()
    soundPool.release()
  }
}
