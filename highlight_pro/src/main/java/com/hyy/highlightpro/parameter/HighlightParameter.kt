package com.hyy.highlightpro.parameter

import android.graphics.RectF
import android.view.View
import com.hyy.highlightpro.shape.HighLightShape

/**
 * Create by hyy on 2021/2/6
 *
 * [HighlightParameter] is a build mode for user to set
 * parameters to build the [GuideView]
 */
class HighlightParameter {

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

    operator fun plus(highlightParameter: HighlightParameter): List<HighlightParameter>{
        return listOf(this, highlightParameter)
    }

    class Builder {
        private val highlightParameter: HighlightParameter = HighlightParameter()

        /**
         * [viewId] is the highLightView's id
         * it will be override by [setHighLightView]
         */
        fun setHighLightViewId(viewId: Int): Builder {
            highlightParameter.highLightViewId = viewId
            return this
        }

        /**
         * [highLightView] is the view which you want to make it highLight
         * And it will make [setHighLightViewId] useless
         */
        fun setHighLightView(highLightView: View): Builder {
            highlightParameter.highLightView = highLightView
            return this
        }

        /**
         * [viewId] is the tipsView's id
         * it will be override by [setTipsViewView]
         */
        fun setTipsViewId(viewId: Int): Builder {
            highlightParameter.tipsViewId = viewId
            return this
        }

        /**
         * [tipsView] is the view which you want to add it on [GuideViewContainer]
         * And it will make [setTipsViewId] useless
         */
        fun setTipsViewView(tipsView: View): Builder {
            highlightParameter.tipsView = tipsView
            return this
        }

        fun setHighLightShape(highLightShape: HighLightShape): Builder {
            highlightParameter.highLightShape = highLightShape
            return this
        }

        fun setHighLightVerticalPadding(padding: Float): Builder {
            highlightParameter.verticalPadding = padding
            return this
        }

        fun setMarginOffset(marginOffset: MarginOffset): Builder {
            highlightParameter.marginOffset = marginOffset
            return this
        }

        fun setConstraints(constraints: List<Constraints>): Builder {
            highlightParameter.constraints.clear()
            highlightParameter.constraints.addAll(constraints)
            return this
        }
        fun setHighLightHorizontalPadding(padding: Float): Builder {
            highlightParameter.horizontalPadding = padding
            return this
        }

        fun build(): HighlightParameter = highlightParameter
    }
}