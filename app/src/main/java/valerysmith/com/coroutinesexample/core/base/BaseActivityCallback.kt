package valerysmith.com.coroutinesexample.core.base

interface BaseActivityCallback {

    fun setLoader(inProgress: Boolean)

    fun showMessage(messageData: MessageData)
}