package valerysmith.com.coroutinesexample.core.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import getFragmentTag
import timber.log.Timber
import valerysmith.com.coroutinesexample.R
import valerysmith.com.coroutinesexample.util.extension.getInputMethodManager

abstract class BaseDialogFragment(
    @StyleRes private val dialogAnimationStyle: Int = R.style.FadeDialogAnimation
) : DialogFragment() {

    private var isDisplayed = false

    fun show(manager: FragmentManager) {
        if (isAdded || isDisplayed) {
            return
        }
        try {
            super.show(manager, getFragmentTag())
            isDisplayed = true
        } catch (exception: Exception) {
            Timber.e(exception, "Show dialog error: %s", getFragmentTag())
        }
    }

    protected fun hideKeyboard() {
        val view: View? = dialog?.currentFocus
        if (view != null) {
            context?.getInputMethodManager()
                ?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        isDisplayed = false
    }

    override fun onActivityCreated(args: Bundle?) {
        super.onActivityCreated(args)
        if (dialogAnimationStyle != 0) {
            dialog?.window
                ?.attributes
                ?.windowAnimations = dialogAnimationStyle
        }
    }
}