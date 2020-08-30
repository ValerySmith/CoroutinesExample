@file:Suppress("unchecked_cast")

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import valerysmith.com.coroutinesexample.util.extension.toast

fun Fragment.getFragmentTag(suffix: String? = null): String =
    this::class.java.simpleName + (suffix ?: "")

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    activity?.toast(text, duration)
}

fun Fragment.toast(resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    activity?.toast(resId, duration)
}

fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}

fun Fragment.setColor(colorRes: Int): Int {
   return ContextCompat.getColor(requireContext(), colorRes)
}

/**
 * Observe nullable value changes in Fragment
 */
fun <T, LD : LiveData<T>> Fragment.observeNullable(liveData: LD, onChanged: (T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        onChanged(it)
    })
}

/**
 * Observe value changes in Fragment
 */
fun <T, LD : LiveData<T>> Fragment.observe(liveData: LD, onChanged: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        it?.let(onChanged)
    })
}