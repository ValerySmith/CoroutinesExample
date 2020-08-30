@file:Suppress("InlinedApi")

package valerysmith.com.coroutinesexample.util.extension

import android.content.Context
import android.os.Vibrator
import android.view.inputmethod.InputMethodManager

fun Context.getInputMethodManager() =
    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.getVibrator() =
    getSystemService(Context.VIBRATOR_SERVICE) as Vibrator