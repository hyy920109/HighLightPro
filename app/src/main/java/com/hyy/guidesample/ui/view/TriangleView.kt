package com.hyy.guidesample.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt
import com.hyy.guidepro.util.dp
import com.hyy.guidesample.R

/**
 *Create by hyy on 2021/4/6
 */
class TriangleView constructor(
    context: Context, attributeSet: AttributeSet
) : View(context, attributeSet) {

    private val bgColor: Int
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    private val direction: Int

    private val path by lazy {
        Path()
    }
    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.TriangleView)
        typedArray.run {
            bgColor = getColor(
                R.styleable.TriangleView_tv_bg_color,
                "#80000000".toColorInt()
            )
            direction = getInteger(R.styleable.TriangleView_tv_arrow_dir, DIR.BOTTOM.dir)
            paint.color = bgColor
        }
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.reset()
        when(direction) {
            DIR.TOP.dir -> {
                path.moveTo(0f, measuredHeight.toFloat())
                path.lineTo(measuredWidth/2f, 0f.dp)
                path.lineTo(measuredWidth*1f, measuredHeight*1f)
                path.close()
                canvas?.drawPath(path, paint)
            }
            DIR.BOTTOM.dir -> {
                path.moveTo(0f, 0f)
                path.lineTo(measuredWidth/2f, measuredHeight*1f)
                path.lineTo(measuredWidth*1f, 0f)
                path.close()
                canvas?.drawPath(path, paint)
            }
            DIR.LEFT.dir -> {
                path.moveTo(measuredWidth*1f, 0f)
                path.lineTo(0f, measuredHeight/2f)
                path.lineTo(measuredWidth*1f, measuredHeight*1f)
                path.close()
                canvas?.drawPath(path, paint)
            }
            DIR.RIGHT.dir -> {
                path.moveTo(0f, 0f)
                path.lineTo(measuredWidth*1f, measuredHeight/2f)
                path.lineTo(0f, measuredHeight*1f)
                path.close()
                canvas?.drawPath(path, paint)
            }
        }

    }

    companion object {
        const val TAG = "TriangleView"
    }

    enum class DIR(val dir: Int){
        LEFT(1), TOP(2), RIGHT(3), BOTTOM(4)
    }
}