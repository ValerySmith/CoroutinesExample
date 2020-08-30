package valerysmith.com.coroutinesexample.core.base

sealed class MessageData {

    data class ToastMessage(val message: String) : MessageData()

    data class SnackbarMessage(
        val message: String,
        val positiveButtonText: String? = null,
        val positiveButtonListener: (() -> Unit)? = null
    ) : MessageData()

    data class DialogMessage(
        val title: String? = null,
        val message: String?,
        val isCancellable: Boolean? = null,
        val positiveButtonText: String? = null,
        val positiveButtonListener: (() -> Unit)? = null,
        val negativeButtonText: String? = null,
        val negativeButtonListener: (() -> Unit)? = null
    ) : MessageData()

    data class FieldMessage(
        val fieldName: String?,
        val message: String?
    ) : MessageData()
}