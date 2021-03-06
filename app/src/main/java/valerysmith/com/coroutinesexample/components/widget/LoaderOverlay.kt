package valerysmith.com.coroutinesexample.components.widget

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import android.widget.FrameLayout
import valerysmith.com.coroutinesexample.R
import valerysmith.com.coroutinesexample.util.AnimationHelper
import valerysmith.com.coroutinesexample.util.extension.gone
import valerysmith.com.coroutinesexample.util.extension.inflate
import valerysmith.com.coroutinesexample.util.extension.visible

class LoaderOverlay @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    style: Int = 0
) : FrameLayout(context, attrs, style) {

    private val alphaInAnimation =
        AnimationHelper.getAlphaAnimation(
            this,
            AnimationHelper.ALPHA_MIN,
            AnimationHelper.ALPHA_MAX,
            ANIMATION_DURATION,
            AccelerateInterpolator(),
            doOnStart = {
                visible()
            }
        )
    private val alphaOutAnimation =
        AnimationHelper.getAlphaAnimation(
            this,
            AnimationHelper.ALPHA_MAX,
            AnimationHelper.ALPHA_MIN,
            ANIMATION_DURATION,
            AccelerateInterpolator(),
            doOnEnd = {
                gone()
            }
        )

    var visible = false
        set(value) {
            field = value
            if (field) {
                alphaOutAnimation.cancel()
                alphaInAnimation.start()
            } else {
                alphaInAnimation.cancel()
                alphaOutAnimation.start()
            }
        }

    init {
        gone()
        inflate(R.layout.view_common_loader_overlay, true)
        alpha = AnimationHelper.ALPHA_MIN
        translationZ = OVERLAY_DEFAULT_TRANSLATION_Z
    }

    companion object {

        private const val ANIMATION_DURATION = 250L
        private const val OVERLAY_DEFAULT_TRANSLATION_Z = 100f
    }
}