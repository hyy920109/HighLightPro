package com.hyy.highlightpro

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.calculateHighLightViewRect
import com.hyy.highlightpro.util.dp
import com.hyy.highlightpro.util.isAttachToWindow
import com.hyy.highlightpro.view.MaskContainer

/**
 * Create by hyy on 2021/2/6
 *
 * All [HighlightPro]'s method implementation show in this class
 * and [HighlightProImpl] is the core class in this library
 */
internal class HighlightProImpl : HighlightViewInteractiveAction {

    internal constructor(activity: Activity) {
        rootView = activity.window.decorView as ViewGroup
        maskContainer = MaskContainer(activity)
    }

    internal constructor(view: ViewGroup) {
        rootView = view
        maskContainer = MaskContainer(view.context)
    }

    internal constructor(fragment: Fragment) {
        if (fragment.view == null)
            throw IllegalStateException("The fragment's view not created yet,please call this after fragment's onViewCreated()")
        if (fragment.isDetached)
            throw IllegalStateException("The fragment have detached. It is not attach to an activity!")
        rootView = fragment.requireActivity().window.decorView as ViewGroup
        fragmentRootView = fragment.view
        isFragmentRoot = true
        maskContainer = MaskContainer(rootView.context)

    }

    companion object {
        const val TAG = "HYY-GuideProImpl"
    }

    private var isFragmentRoot: Boolean = false
    private var fragmentRootView: View? = null
    private var curIndex: Int = 0
    private val highlightParameters: MutableList<List<HighlightParameter>> = mutableListOf()
    private var hasShow: Boolean = false
    private val rootView: ViewGroup
    private val maskContainer: MaskContainer
    private var released = false
    private var showCallback: ((index: Int) -> Unit)? = null
    private var dismissCallback: (() -> Unit)? = null
    private var clickCallback: ((View) -> Unit)? = null
    private var autoNext = true
    private var needAnchorTipView = true

    private val onClickListener = View.OnClickListener {
        clickCallback?.invoke(it)
        if (autoNext) {
            showNextHighLightView()
        }
    }

    private val onDismissClickListener = View.OnClickListener {
        clickCallback?.invoke(it)
        dismiss()
    }

    override fun show() {
        if (released) return
        println("$TAG show")
        maskContainer.setOnClickCallback(onClickListener)
        maskContainer.setOnDismissClickCallback(onDismissClickListener)
        //if constructor's param is activity or view wo care about rootView's attachedToWindow
        //if constructor's param is fragment we care about [fragmentRootView]'s width is not 0
        if ((isFragmentRoot.not() && rootView.isAttachToWindow()) ||
            (isFragmentRoot && fragmentRootView?.width != 0)
        ) {
            if (maskContainer.parent == null) {
                //add guideViewContainer to rootView
                rootView.addView(
                    maskContainer,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
                if (maskContainer.interceptBackPressed) {
                    maskContainer.apply {
                        //three line below enable maskContainer focusable and got focused
                        isFocusable = true
                        isFocusableInTouchMode = true
                        requestFocus()
                        setOnBackPressedCallback {
                            showNextHighLightView()
                        }
                    }
                }

                showNextHighLightView()
            }
        } else {
            if (isFragmentRoot) {
                fragmentRootView?.doOnPreDraw {
                    println("$TAG fragmentRootView pre draw")
                    //ensure this method will be call once
                    if (hasShow) return@doOnPreDraw
                    hasShow = false
                    show()
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
    }

    /**
     * this is the function which real show highLightView rect and tipView
     */
    private fun showNextHighLightView() {
        if (released) return

        println("$TAG showNextHighLightView")

        if (hasHighLightView().not()) {
            dismiss()
        } else {
            highlightParameters[0].forEach {
                checkOrInitParameter(it)
            }
            showCallback?.invoke(curIndex)
            curIndex++
            maskContainer.setRootWidth(rootView.width - rootView.paddingLeft - rootView.paddingRight)//ignore padding
            maskContainer.setRootHeight(rootView.height - rootView.paddingTop - rootView.paddingBottom)//ignore padding
            maskContainer.setHighLightParameters(highlightParameters[0])
            highlightParameters.removeAt(0)
        }
    }

    /**
     * check valid for each parameter if invalid set default value
     */
    private fun checkOrInitParameter(parameter: HighlightParameter) {

        if (parameter.highLightView == null) {
            parameter.highLightView = rootView.findViewById(parameter.highLightViewId)
        }

        if (parameter.tipsView == null && checkTipViewIdIsValid(parameter)) {
            parameter.tipsView = LayoutInflater.from(maskContainer.context)
                .inflate(parameter.tipsViewId, maskContainer, false)
        }

        if (parameter.highlightShape == null) {
            parameter.highlightShape = RectShape(2f.dp, 2f.dp, 2f.dp)
        }

        parameter.calculateHighLightViewRect(rootView)
    }

    private fun checkTipViewIdIsValid(parameter: HighlightParameter): Boolean =
        parameter.tipsViewId != -1


    private fun hasHighLightView(): Boolean = highlightParameters.isNotEmpty()

    override fun dismiss() {
        if (released) return
        //release every thing
        released = true
        //todo if we want have a dismiss animation these code need rewrite
        maskContainer.isFocusable = false
        maskContainer.clearFocus()

        rootView.removeView(maskContainer)
        maskContainer.removeAllViews()
        dismissCallback?.invoke()
    }

    fun setBackgroundColor(color: Int) {
        maskContainer.setBackgroundColor(color)
    }

    fun setGuideViewParameters(highlightParameters: List<HighlightParameter>) {
        if (released) return
        this.highlightParameters.add(highlightParameters)
    }

    fun setGuideViewParameter(block: () -> HighlightParameter) {
        if (released) return
        highlightParameters.add(listOf(block.invoke()))
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
        this.maskContainer.enableHighlight = enableHighlight
    }

    fun interceptBackPressed(interceptBackPressed: Boolean) {
        this.maskContainer.interceptBackPressed = interceptBackPressed
    }

    fun needAnchorTipView(needAnchorTipView: Boolean) {
        this.needAnchorTipView = needAnchorTipView
        this.maskContainer.needAnchorTipView = needAnchorTipView
    }

}