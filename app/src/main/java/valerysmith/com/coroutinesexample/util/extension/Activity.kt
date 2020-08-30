@file:Suppress("unchecked_cast")

import android.app.Activity
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import valerysmith.com.coroutinesexample.util.extension.getInputMethodManager

fun Activity.getScreenParams(): DisplayMetrics {
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    return metrics
}

fun Activity.getNavigationBarHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId)
    else 0
}

fun Activity.getStatusBarHeight(): Int {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) result = resources.getDimensionPixelSize(resourceId)
    return result
}

fun Activity.getToolbarHeight(): Int {
    val value = TypedValue()
    return if (theme.resolveAttribute(android.R.attr.actionBarSize, value, true))
        TypedValue.complexToDimensionPixelSize(value.data, resources.displayMetrics)
    else 0
}

fun Activity.hideKeyboard() {
    val view = currentFocus
    if (view != null) {
        getInputMethodManager().hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Activity.setColor(@ColorRes colorId : Int) : Int {
    return ContextCompat.getColor(this, colorId)
}

fun Activity.hideSystemNavigation() {
    val decorView = window?.decorView
    val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    decorView?.systemUiVisibility = uiOptions
}

/**
 * Observe nullable value changes in Activity
 */
fun <T, LD : LiveData<T>> AppCompatActivity.observeNullable(liveData: LD, onChanged: (T?) -> Unit) {
    liveData.observe(this, Observer {
        onChanged(it)
    })
}

/**
 * Observe value changes in Activity
 */
fun <T, LD : LiveData<T>> AppCompatActivity.observe(liveData: LD, onChanged: (T) -> Unit) {
    liveData.observe(this, Observer {
        it?.let(onChanged)
    })
}