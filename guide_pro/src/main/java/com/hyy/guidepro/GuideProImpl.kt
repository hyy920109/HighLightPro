package com.hyy.guidepro

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import com.hyy.guidepro.parameter.GuideParameter
import com.hyy.guidepro.shape.RectShape
import com.hyy.guidepro.view.MaskContainer

/**
 * Create by hyy on 2021/2/6
 *
 * All [GuidePro]'s method implementation show in this class
 * and [GuideProImpl] is the core class in this library
 */
class GuideProImpl : GuideViewInteractiveAction {

    companion object {
        const val TAG = "GuideViewImpl"
    }

    private var curIndex: Int = 0
    private val guideParameters: MutableList<List<GuideParameter>> = mutableListOf()
    private var hasShow: Boolean = false
    private val rootView: ViewGroup
    private val maskContainer: MaskContainer
    private var released = false
    private var showCallback: ((index: Int)->Unit)? = null
    private var dismissCallback: (()->Unit)? = null
    private var clickCallback: ((View) -> Unit)? = null
    private var autoNext = true

    private val onClickListener = View.OnClickListener {
        clickCallback?.invoke(it)
        if (autoNext) {
            showNextHighLightView()
        }
    }

    internal constructor(activity: Activity) {
        rootView = activity.window.decorView as ViewGroup
        maskContainer = MaskContainer(activity)
    }

    internal constructor(view: ViewGroup) {
        rootView = view
        maskContainer = MaskContainer(view.context)
    }

    override fun show() {
        if (released) return

        //todo give user access to intercept click event
//        if (!intercept) {
            maskContainer.setOnClickListener(onClickListener)
//        }

        if (rootView.isAttachToWindow()) {
            if (maskContainer.parent == null) {
                //add guideViewContainer to rootView
                rootView.addView(
                    maskContainer,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
                showNextHighLightView()
            }
        } else {
            rootView.doOnPreDraw {
                //ensure this method will be call once
                if (hasShow) return@doOnPreDraw
                hasShow = false
                show()
            }
        }
    }

    /**
     * this is the function which real show highLightView rect and tipView
     */
    private fun showNextHighLightView() {
        if (released) return

//        if (rootView.isAttachToWindow().not()) {
//            show()
//            return
//        }
        println("$TAG showNextHighLightView")

        if (hasHighLightView().not()) {
            dismiss()
        } else {
            guideParameters[0].forEach {
                checkOrInitParameter(it)
            }
            showCallback?.invoke(curIndex)
            curIndex++
            maskContainer.setRootWidth(rootView.width)//ignore padding
            maskContainer.setRootHeight(rootView.height)//ignore padding
            maskContainer.setHighLightParameters(guideParameters[0])
            guideParameters.removeAt(0)
        }
    }

    /**
     * check valid for each parameter if invalid set default value
     */
    private fun checkOrInitParameter(parameter: GuideParameter) {

        if (parameter.highLightView == null) {
            parameter.highLightView = rootView.findViewById(parameter.highLightViewId)
        }

        if (parameter.tipsView == null) {
            checkViewId(parameter)
            parameter.tipsView = LayoutInflater.from(maskContainer.context)
                .inflate(parameter.tipsViewId, null)
        }

        if (parameter.highLightShape == null) {
            parameter.highLightShape = RectShape(2f.dp, 2f.dp, 2f.dp)
        }

        parameter.calculateHighLightViewRect()
    }

    private fun checkViewId(parameter: GuideParameter) {
        if (parameter.tipsViewId == -1) throw IllegalArgumentException("the viewId is Illegal. please check your arguments")
    }

    private fun hasHighLightView(): Boolean = guideParameters.isNotEmpty()

    override fun dismiss() {
        if (released) return
        //release every thing
        released = true
        //todo if we want have a dismiss animation these code need rewrite
        rootView.removeView(maskContainer)
        maskContainer.removeAllViews()
        dismissCallback?.invoke()
    }

    fun setBackgroundColor(color: Int) {
        maskContainer.setBackgroundColor(color)
    }

    fun setGuideViewParameters(guideParameters: List<GuideParameter>) {
        if (released) return
        this.guideParameters.add(guideParameters)
    }

    fun setGuideViewParameter(block: () -> GuideParameter) {
        if (released) return
        guideParameters.add(listOf(block.invoke()))
    }

    fun setOnShowCallback(showCallback: (Int) -> Unit) {
        this.showCallback = showCallback
    }

    fun setOnDismissCallback(dismissCallback: () -> Unit) {
        this.dismissCallback = dismissCallback
    }

    fun setOnGuideViewClickCallback(clickCallback: (View) -> Unit) {
        this.clickCallback = clickCallback
    }

    fun enableHighlight(enableHighlight: Boolean) {
        this.maskContainer.enableHighlight(enableHighlight)
    }

}