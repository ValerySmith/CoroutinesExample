package valerysmith.com.coroutinesexample.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import observe
import timber.log.Timber

abstract class BaseFragment(
    private val layoutResId: Int,
    private val baseViewModel: BaseViewModel? = null
) : Fragment() {


    @Suppress("UNREACHABLE_CODE")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        return if (layoutResId > 0) {
            inflater.inflate(layoutResId, container, false)
        } else {
            null
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag(this::class.java.simpleName)
        Timber.d("onViewCreated()")
        baseViewModel?.let {
            viewLifecycleOwner.lifecycle.addObserver(it)
        }
        initializeView()
        onBindLiveData()
    }

    protected abstract fun initializeView()

    protected fun setLoading(inProgress: Boolean) {
        activity?.let {
            (it as BaseActivityCallback).setLoader(inProgress)
        }
    }

    protected open fun showMessage(messageData: MessageData) {
        activity?.let {
            (it as BaseActivityCallback).showMessage(messageData)
        }
    }

    private fun onBindLiveData() {
        baseViewModel?.let { viewModel ->
            observe(viewModel.progressLoadingEvent) { setLoading(it) }
            observe(viewModel.messageDataEvent) { showMessage(it) }
        }
    }


}