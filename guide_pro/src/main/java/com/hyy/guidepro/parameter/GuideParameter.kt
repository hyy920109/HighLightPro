package com.hyy.guidepro.parameter

import android.graphics.RectF
import android.view.View
import com.hyy.guidepro.shape.HighLightShape

/**
 * Create by hyy on 2021/2/6
 *
 * [GuideParameter] is a build mode for user to set
 * parameters to build the [GuideView]
 */
class GuideParameter {

    internal var highLightViewId: Int = -1

    internal var highLightView: View? = null

    internal var tipsViewId: Int = -1

    internal var tipsView: View? = null

    internal var highLightShape: HighLightShape? = null

    internal var rect: RectF = RectF()

    internal var horizontalPadding: Float = 0f

    internal var verticalPadding: Float = 0f

    internal var marginOffset: MarginOffset = MarginOffset()
    internal val constraints = mutableListOf(Constraints.TopToBottomOfHighlight, Constraints.StartToStartOfHighlight)

    fun setHighLightViewId(viewId: Int) {
        this.highLightViewId = viewId
    }

    fun setHighLightView(highLightView: View) {
        this.highLightView = highLightView
    }

    operator fun plus(guideParameter: GuideParameter): List<GuideParameter>{
        return listOf(this, guideParameter)
    }

    class Builder {
        private val guideParameter: GuideParameter = GuideParameter()

        /**
         * [viewId] is the highLightView's id
         * it will be override by [setHighLightView]
         */
        fun setHighLightViewId(viewId: Int): Builder {
            guideParameter.highLightViewId = viewId
            return this
        }

        /**
         * [highLightView] is the view which you want to make it highLight
         * And it will make [setHighLightViewId] useless
         */
        fun setHighLightView(highLightView: View): Builder {
            guideParameter.highLightView = highLightView
            return this
        }

        /**
         * [viewId] is the tipsView's id
         * it will be override by [setTipsViewView]
         */
        fun setTipsViewId(viewId: Int): Builder {
            guideParameter.tipsViewId = viewId
            return this
        }

        /**
         * [tipsView] is the view which you want to add it on [GuideViewContainer]
         * And it will make [setTipsViewId] useless
         */
        fun setTipsViewView(tipsView: View): Builder {
            guideParameter.tipsView = tipsView
            return this
        }

        fun setHighLightShape(highLightShape: HighLightShape): Builder {
            guideParameter.highLightShape = highLightShape
            return this
        }

        fun setHighLightVerticalPadding(padding: Float): Builder {
            guideParameter.verticalPadding = padding
            return this
        }

        fun setMarginOffset(marginOffset: MarginOffset): Builder {
            guideParameter.marginOffset = marginOffset
            return this
        }

        fun setConstraints(constraints: List<Constraints>): Builder {
            guideParameter.constraints.clear()
            guideParameter.constraints.addAll(constraints)
            return this
        }
        fun setHighLightHorizontalPadding(padding: Float): Builder {
            guideParameter.horizontalPadding = padding
            return this
        }

        fun build(): GuideParameter = guideParameter
    }
}