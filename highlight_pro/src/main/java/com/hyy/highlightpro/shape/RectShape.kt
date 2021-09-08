package com.hyy.highlightpro.shape

import android.graphics.Path
import android.graphics.RectF

/**
 *Create by hyy on 2021/2/7
 */
class RectShape(private val rx: Float = 0f, private val ry: Float = 0f, private val radius: Float = 0f) : HighlightShape(radius) {

    override fun initRect(rectF: RectF) {
        super.initRect(rectF)
        rect?.run {
            path.reset()
            path.addRoundRect(this, rx, ry, Path.Direction.CW)
        }
    }
}