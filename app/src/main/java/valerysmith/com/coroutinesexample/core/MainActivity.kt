package valerysmith.com.coroutinesexample.core

import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_bottom_nav_view.*
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import setColor
import timber.log.Timber
import valerysmith.com.coroutinesexample.R
import valerysmith.com.coroutinesexample.core.base.BaseActivity
import valerysmith.com.coroutinesexample.util.extension.animateGone
import valerysmith.com.coroutinesexample.util.extension.animateVisible

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel by ViewModelCompat.viewModel(this, MainActivityViewModel::class.java)

    private var hasBackAction = true

    override fun initializeViews() {
        setupBottomView()
        setupNavigation()
        lifecycle.addObserver(viewModel)
    }


    private fun setupBottomView() {
        bottom_nav_view.apply {

        }

        val badgeDrawable: BadgeDrawable = bottom_nav_view.getOrCreateBadge(R.id.favoriteFragment)
        badgeDrawable.apply {
            isVisible = true
            backgroundColor = setColor(R.color.colorWhite)
            badgeTextColor = setColor(R.color.colorBlack)
            maxCharacterCount = 5
            number = 10
        }
    }


    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(R.navigation.nav_graph)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Timber.d("destination> ${destination.label}")
            setLoader(false)
            hasBackAction = true
            val screenParams = when (destination.id) {
                R.id.bottomNavViewFragment -> {
                    ScreenParams(
                        R.drawable.bg_main,
                        true,
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                    )
                }
                else -> ScreenParams(
                    R.drawable.bg_main,
                    false,
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                )
            }
            setScreenParams(screenParams)
        }
        bottom_nav_view.setupWithNavController(navController)

    }

    private fun setScreenParams(params: ScreenParams) {
        with(params) {
            background?.let {
                window.setBackgroundDrawable(
                    ContextCompat.getDrawable(
                        this@MainActivity,
                        it
                    )
                )
            }
            bottomNavigationVisible?.let { if (it) bottom_nav_view.animateVisible() else bottom_nav_view.animateGone() }
            softInputMode?.let { window.setSoftInputMode(it) }
        }
    }

    override fun onBackPressed() {
        if (hasBackAction) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    private data class ScreenParams(
        @DrawableRes val background: Int? = null,
        val bottomNavigationVisible: Boolean? = null,
        val softInputMode: Int? = null
    )

}
