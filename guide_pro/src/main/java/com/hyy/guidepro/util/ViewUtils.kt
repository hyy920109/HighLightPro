package com.hyy.guidepro.util

import android.content.res.Resources
import android.graphics.RectF
import android.os.Build
import android.util.TypedValue
import android.view.View
import com.hyy.guidepro.parameter.GuideParameter

/**
 *Create by hyy on 2021/2/6
 */

fun View.isAttachToWindow(): Boolean {
    return if (Build.VERSION.SDK_INT >= 19) {
        isAttachedToWindow
    } else {
        windowToken != null
    }
}

fun View?.getRectOnScreen(): RectF {
    if (this == null) {
        return RectF()
    }
    val pos = intArrayOf(0, 0)
    getLocationOnScreen(pos)

    return RectF().apply {
        left = pos[0].toFloat()
        top = pos[1].toFloat()
        right = pos[0].toFloat() + width
        bottom = pos[1].toFloat() + height
    }
}

fun GuideParameter.calculateHighLightViewRect() {
    println("HYY->> $highLightView")
    val rectOnScreen = highLightView.getRectOnScreen()

    rect = rectOnScreen
    rect.run {
        left -= horizontalPadding
        top -= verticalPadding
        right += horizontalPadding
        bottom += verticalPadding

        highLightShape?.initRect(this)
    }
}

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Int.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()