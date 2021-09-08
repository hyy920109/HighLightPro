package com.hyy.highlightpro.shape

import android.graphics.Path
import android.graphics.RectF

/**
 *Create by hyy on 2021/2/7
 */
class OvalShape(private val radius: Float = 0f) : HighlightShape(radius) {

    override fun initRect(rectF: RectF) {
        super.initRect(rectF)
        rect?.run {
            path.reset()
            path.addOval(this,Path.Direction.CW)
        }
    }
}