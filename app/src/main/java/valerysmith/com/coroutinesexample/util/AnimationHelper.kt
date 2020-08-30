package valerysmith.com.coroutinesexample.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Interpolator

object AnimationHelper {

    const val ALPHA_MIN = 0f
    const val ALPHA_MAX = 1f
    private const val ALPHA_PROPERTY_NAME = "alpha"
    private const val TRANSLATION_X_PROPERTY_NAME = "translationX"
    private const val DEFAULT_DURATION = 0L

    fun getAlphaAnimation(
        target: View,
        fromValue: Float,
        toValue: Float,
        duration: Long = DEFAULT_DURATION,
        interpolator: Interpolator? = null,
        doOnStart: (() -> Unit)? = null,
        doOnEnd: (() -> Unit)? = null,
        doOnCancel: (() -> Unit)? = null
    ): Animator = ObjectAnimator.ofFloat(target, ALPHA_PROPERTY_NAME, fromValue, toValue)
        .apply {
            this.interpolator = interpolator
            if (duration != DEFAULT_DURATION) this.duration = duration
            if (doOnStart != null || doOnEnd != null || doOnCancel != null) addListener(
                getDefaultAnimatorListener(
                    doOnStart,
                    doOnEnd,
                    doOnCancel
                )
            )
        }

    private fun getDefaultAnimatorListener(
        doOnStart: (() -> Unit)? = null,
        doOnEnd: (() -> Unit)? = null,
        doOnCancel: (() -> Unit)? = null
    ): Animator.AnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            doOnEnd?.invoke()
        }

        override fun onAnimationCancel(animation: Animator?) {
            doOnCancel?.invoke()
        }

        override fun onAnimationStart(animation: Animator?) {
            doOnStart?.invoke()
        }
    }

    fun getShakerAnimator(
        target: View,
        duration: Long = DEFAULT_DURATION,
        interpolator: Interpolator? = null,
        doOnStart: (() -> Unit)? = null,
        doOnEnd: (() -> Unit)? = null,
        doOnCancel: (() -> Unit)? = null
    ): Animator =
        ObjectAnimator.ofFloat(target, TRANSLATION_X_PROPERTY_NAME, 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f)
            .apply {
                if (duration != DEFAULT_DURATION) this.duration = duration
                this.interpolator = interpolator
                if (doOnStart != null || doOnEnd != null || doOnCancel != null) addListener(
                    getDefaultAnimatorListener(
                        doOnStart,
                        doOnEnd,
                        doOnCancel
                    )
                )
            }
}