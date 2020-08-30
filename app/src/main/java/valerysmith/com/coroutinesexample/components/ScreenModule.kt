package valerysmith.com.coroutinesexample.components

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import valerysmith.com.coroutinesexample.core.MainActivityViewModel

val screenModule = module {

    viewModel { MainActivityViewModel() }

}