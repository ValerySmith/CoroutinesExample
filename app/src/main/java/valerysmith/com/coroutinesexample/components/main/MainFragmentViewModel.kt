package valerysmith.com.coroutinesexample.components.main

import valerysmith.com.coroutinesexample.core.base.BaseViewModel

class MainFragmentViewModel : BaseViewModel() {

    fun onNavigateToBottomViewFragment() {
        navigateTo(MainFragmentDirections.actionMainFragmentToBottomNavViewFragment())
    }

}