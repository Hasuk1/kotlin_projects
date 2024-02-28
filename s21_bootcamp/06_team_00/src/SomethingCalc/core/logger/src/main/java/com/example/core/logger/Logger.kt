package com.example.core.logger

import android.util.Log

object Logger {
  fun info(message: String) {
    Log.i("AppLogger", "Message: $message")
  }

  fun error(error: String) {
    Log.e("AppLogger", "Error: $error")
  }

  fun warning(warning: String) {
    Log.w("AppLogger", "Warning: $warning")
  }
}