package com.hyy.highlightpro

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.view.MaskContainer

/**
 *Create by hyy on 2021/2/6
 *
 * interface of guide View
 * detail implementation in [HighlightProImpl]
 * @see com.hyy.highlightpro.HighlightProImpl
 */
class HighlightPro : HighlightViewInteractiveAction {

    private val highlightProImpl: HighlightProImpl

    private constructor(activity: Activity) {
        highlightProImpl = HighlightProImpl(activity)
    }

    private constructor(fragment: Fragment) {
        highlightProImpl = HighlightProImpl(fragment)
    }

    private constructor(view: ViewGroup) {
        highlightProImpl = HighlightProImpl(view)
    }

    /**
     * [show] is a final method to call then show a guide or a popup window
     */
    override fun show() {
        highlightProImpl.show()
    }

    /**
     * Dismiss [HighlightPro] auto in common.
     * we can manually call this method([dismiss]) to dismiss [HighlightPro]
     */
    override fun dismiss() {
        highlightProImpl.dismiss()
    }

    /**
     * Set your mask background [color] of [MaskContainer]
     */
    fun setBackgroundColor(color: Int): HighlightPro {
        highlightProImpl.setBackgroundColor(color)
        return this
    }

    /**
     * Set a List of [HighlightParameter] so we can show more Highlight at once
     */
    fun setHighlightParameters(highlightParameters: List<HighlightParameter>): HighlightPro {
        highlightProImpl.setGuideViewParameters(highlightParameters)
        return this
    }

    /**
     * Set single [HighlightParameter]
     *
     * With [setHighlightParameter] we can use kotlin function with return
     */
    fun setHighlightParameter(block: ()-> HighlightParameter): HighlightPro {
        highlightProImpl.setGuideViewParameter(block = block)
        return this
    }

    /**
     * [showCallback] is a function which will be invoked when the [HighlightPro] show
     */
    fun setOnShowCallback(showCallback: (Int) -> Unit) : HighlightPro{
        highlightProImpl.setOnShowCallback(showCallback = showCallback)
        return this
    }

    /**
     * [dismissCallback] is a function which will be invoked when the [HighlightPro] dismiss
     */
    fun setOnDismissCallback(dismissCallback: () -> Unit) : HighlightPro{
        highlightProImpl.setOnDismissCallback(dismissCallback)
        return this
    }

    /**
     * [clickCallback] is a callback when user clicked any place in [MaskContainer]
     */
    fun setOnMaskViewClickCallback(clickCallback: (View) -> Unit): HighlightPro {
        highlightProImpl.setOnGuideViewClickCallback(clickCallback)
        return this
    }

    /**
     * [enableHighlight] give you access to make Highlight dismiss and show UI like popup window
     */
    fun enableHighlight(enableHighlight: Boolean): HighlightPro {
        highlightProImpl.enableHighlight(enableHighlight)
        return this
    }

    /**
     * [interceptBackPressed] is [true] will intercept activity onBackPressed and [HighlightPro] will dismiss
     */
    fun interceptBackPressed(interceptBackPressed: Boolean): HighlightPro {
        highlightProImpl.interceptBackPressed(interceptBackPressed)
        return this
    }

    companion object {

        /**
         * DecorView of [activity] treat as the rootView
         */
        fun with(activity: Activity): HighlightPro {
            return HighlightPro(activity)
        }

        /**
         * DecorView of [fragment]'s [activity] treat as the rootView
         */
        fun with(fragment: Fragment): HighlightPro {
            return HighlightPro(fragment)
        }

        /**
         * the [view] treat as rootView
         */
        fun with(view: ViewGroup): HighlightPro {
            return HighlightPro(view)
        }
    }
}