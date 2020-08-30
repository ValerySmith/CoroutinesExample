package valerysmith.com.coroutinesexample.core.base

import androidx.annotation.CallSuper
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import valerysmith.com.coroutinesexample.util.SingleLiveEvent

open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val _navigationEvent = SingleLiveEvent<Int>()
    val navigationEvent: LiveData<Int>
        get() = _navigationEvent
    private val _navigationEventDirections = SingleLiveEvent<NavDirections>()
    val navDirectionsEvent: LiveData<NavDirections>
        get() = _navigationEventDirections
    private val _progressLoadingEvent = MutableLiveData<Boolean>()
    val progressLoadingEvent: LiveData<Boolean>
        get() = _progressLoadingEvent
    private val _messageDataEvent = SingleLiveEvent<MessageData>()
    val messageDataEvent: LiveData<MessageData>
        get() = _messageDataEvent
    private val _isAppForegroundLiveDate = MutableLiveData<Boolean>()
    val isAppForegroundLiveDate: LiveData<Boolean>
        get() = _isAppForegroundLiveDate

    init {
        hideLoading()
        _isAppForegroundLiveDate.observeForever {
            Timber.tag(this::class.java.simpleName)
            Timber.d("goes ${if (it) "foreground" else "background"}")
        }
    }

    protected fun showLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _progressLoadingEvent.postValue(true)
        }
    }

    protected fun hideLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _progressLoadingEvent.postValue(false)
        }
    }

    protected fun showMessage(messageData: MessageData) {
        viewModelScope.launch(Dispatchers.Main) {
            _messageDataEvent.value = messageData
        }
    }

    protected fun navigateTo(actionId: Int) {
        _navigationEvent.value = actionId
    }

    protected fun navigateTo(navDirections: NavDirections) {
        _navigationEventDirections.value = navDirections
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        Timber.tag(this::class.java.simpleName)
        Timber.d("onCleared()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        _isAppForegroundLiveDate.value = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        _isAppForegroundLiveDate.value = false
    }
}