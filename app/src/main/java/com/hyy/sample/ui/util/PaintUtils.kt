package com.hyy.sample.ui.util

import android.graphics.*
import com.hyy.highlightpro.util.dp

/**
 *Create by hyy on 2021/4/12
 */
object PaintUtils {

    fun getDashPaint(): Paint {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f.dp
        paint.pathEffect = DashPathEffect(floatArrayOf(8f.dp, 8f.dp), 0f)
        paint.maskFilter = BlurMaskFilter(20f, BlurMaskFilter.Blur.SOLID)
        return paint
    }

    fun getDiscretePaint(): Paint {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 2f.dp
        paint.pathEffect = DiscretePathEffect(8f.dp, 8f.dp)
        paint.maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.SOLID)
        return paint
    }

}