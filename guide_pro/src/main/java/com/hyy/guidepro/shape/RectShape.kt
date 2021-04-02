package com.hyy.guidepro.shape

import android.graphics.Path
import android.graphics.RectF

/**
 *Create by hyy on 2021/2/7
 */
class RectShape(private val rx: Float = 0f, private val ry: Float = 0f, private val radius: Float = 0f) : HighLightShape(radius) {

    override fun initRect(rectF: RectF) {
        super.initRect(rectF)
        rect?.run {
            path.addRoundRect(this, rx, ry, Path.Direction.CW)
        }
    }
}