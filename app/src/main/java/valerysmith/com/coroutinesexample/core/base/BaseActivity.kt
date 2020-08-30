package valerysmith.com.coroutinesexample.core.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import hideKeyboard
import timber.log.Timber
import valerysmith.com.coroutinesexample.R
import valerysmith.com.coroutinesexample.components.widget.LoaderOverlay
import valerysmith.com.coroutinesexample.util.extension.toast

abstract class BaseActivity(
    @LayoutRes private val layoutResId: Int
): AppCompatActivity(), BaseActivityCallback {

    private var alertDialog: AlertDialogFragment? = null
    protected var rootView: ViewGroup? = null
    private lateinit var loaderOverlay: LoaderOverlay

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setTheme(R.style.AppTheme)
        if (layoutResId != 0) {
            setContentView(layoutResId)
        }
        rootView = findViewById(android.R.id.content)
        loaderOverlay = LoaderOverlay(this)
            .apply {
                layoutParams = ViewGroup.LayoutParams(
                    MATCH_PARENT,
                    MATCH_PARENT
                )
            }
        rootView!!.addView(loaderOverlay)
        initializeViews()
    }

    abstract fun  initializeViews()

    override fun setLoader(inProgress: Boolean) {
        loaderOverlay.visible = inProgress
    }

    override fun showMessage(messageData: MessageData) {
        when (messageData) {
            is MessageData.ToastMessage -> {
                Timber.d("Toast message:> ${messageData.message}")
                toast(messageData.message)
            }
            is MessageData.SnackbarMessage -> {
                Timber.d("Snackbar message:> ${messageData.message}")
                val snackbar = Snackbar.make(
                    rootView as ViewGroup,
                    messageData.message,
                    Snackbar.LENGTH_INDEFINITE
                )
                if (!messageData.positiveButtonText.isNullOrBlank()) {
                    snackbar.setAction(messageData.positiveButtonText) {
                        messageData.positiveButtonListener?.invoke()
                    }
                }
                snackbar.show()
            }
            is MessageData.DialogMessage -> {
                Timber.d("Dialog message:> ${messageData.message}")
                hideKeyboard()
                alertDialog?.dismissAllowingStateLoss()
                alertDialog = AlertDialogFragment.newInstance(messageData)
                    .also {
                        it.show(supportFragmentManager)
                    }
            }
            is MessageData.FieldMessage -> {
                Timber.d("Field message:> ${messageData.fieldName} > ${messageData.message}")
            }
        }
    }
}