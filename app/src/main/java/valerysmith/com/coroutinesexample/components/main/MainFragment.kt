package valerysmith.com.coroutinesexample.components.main

import android.os.Bundle
import android.view.View
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import valerysmith.com.coroutinesexample.R
import valerysmith.com.coroutinesexample.core.base.BaseFragment

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewModel : MainFragmentViewModel by sharedViewModel()

    override fun initializeView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onNavigateToBottomViewFragment()
    }
}