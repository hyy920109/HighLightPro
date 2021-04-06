package com.hyy.guidepro.shape

import android.graphics.Path
import android.graphics.RectF
import kotlin.math.max

/**
 *Create by hyy on 2021/2/7
 */
class CircleShape(private val radius: Float = 0f) : HighLightShape(radius) {

    override fun initRect(rectF: RectF) {
        super.initRect(rectF)
        rect?.run {
            path.reset()
            path.addCircle((left+right)/2,(top+bottom)/2, max(height(),width()) /2,Path.Direction.CW)
        }
    }
}