package com.hyy.guidepro

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.hyy.guidepro.parameter.GuideParameter
import com.hyy.guidepro.view.MaskContainer

/**
 *Create by hyy on 2021/2/6
 *
 * interface of guide View
 * detail implementation in [GuideProImpl]
 * @see com.hyy.guideplus.GuideViewImpl
 */
class GuidePro : GuideViewInteractiveAction {

    private val guideProImpl: GuideProImpl

    private constructor(activity: Activity) {
        guideProImpl = GuideProImpl(activity)
    }

    private constructor(view: ViewGroup) {
        guideProImpl = GuideProImpl(view)
    }

    /**
     * [show] is a final method to call then show a guide or a popup window
     */
    override fun show() {
        guideProImpl.show()
    }

    /**
     * Dismiss [GuidePro] auto in common.
     * we can manually call this method([dismiss]) to dismiss [GuidePro]
     */
    override fun dismiss() {
        guideProImpl.dismiss()
    }

    /**
     * Set your mask background [color] of [MaskContainer]
     */
    fun setBackgroundColor(color: Int): GuidePro {
        guideProImpl.setBackgroundColor(color)
        return this
    }

    /**
     * Set a List of [GuideParameter] so we can show more Highlight at once
     */
    fun setGuideViewParameters(guideParameters: List<GuideParameter>): GuidePro {
        guideProImpl.setGuideViewParameters(guideParameters)
        return this
    }

    /**
     * Set single [GuideParameter]
     *
     * With [setGuideViewParameter] we can use kotlin function with return
     */
    fun setGuideViewParameter(block: ()-> GuideParameter): GuidePro {
        guideProImpl.setGuideViewParameter(block = block)
        return this
    }

    /**
     * [showCallback] is a function which will be invoked when the [GuidePro] show
     */
    fun setOnShowCallback(showCallback: (Int) -> Unit) : GuidePro{
        guideProImpl.setOnShowCallback(showCallback = showCallback)
        return this
    }

    /**
     * [dismissCallback] is a function which will be invoked when the [GuidePro] dismiss
     */
    fun setOnDismissCallback(dismissCallback: () -> Unit) : GuidePro{
        guideProImpl.setOnDismissCallback(dismissCallback)
        return this
    }

    /**
     * [clickCallback] is a callback when user clicked any place in [GuideViewContainer]
     */
    fun setOnGuideViewClickCallback(clickCallback: (View) -> Unit): GuidePro {
        guideProImpl.setOnGuideViewClickCallback(clickCallback)
        return this
    }

    /**
     * [enableHighlight] give you access to make Highlight dismiss and show UI like popup window
     */
    fun enableHighlight(enableHighlight: Boolean): GuidePro {
        guideProImpl.enableHighlight(enableHighlight)
        return this
    }

    companion object {

        /**
         * DecorView of [activity] treat as the rootView
         */
        fun with(activity: Activity): GuidePro {
            return GuidePro(activity)
        }

        /**
         * the [view] treat as rootView
         */
        fun withView(view: ViewGroup): GuidePro {
            return GuidePro(view)
        }
    }
}