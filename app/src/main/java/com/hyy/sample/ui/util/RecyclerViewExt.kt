package com.hyy.sample.ui.util

import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by heyangyang on 5/24/21.
 */

fun RecyclerView.setOnItemClickAndLongClickedListener(
        itemClickListener: ((e: MotionEvent, view: View, position: Int) -> Boolean)?,
        itemLongClickListener: ((e: MotionEvent, view: View, position: Int) -> Boolean)?,
) {
    val gestureDetector = GestureDetectorCompat(context, object : GestureDetectorCompat.CustomOnGestureListener {
        override fun onDown(e: MotionEvent): Boolean {
            return false
        }

        override fun onShowPress(e: MotionEvent) {
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            e.let {
                findChildViewUnder(it.x, it.y)?.let { child ->
                    return itemClickListener?.invoke(it, child, getChildAdapterPosition(child)) ?: false
                }
            }
            return false
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            return false
        }

        override fun onLongPress(e: MotionEvent) {

        }

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            return false
        }

        override fun onUp(e1: MotionEvent, e2: MotionEvent, deltaX: Float, deltaY: Float): Boolean {
            return false
        }

        override fun onLongClicked(e: MotionEvent): Boolean {
            e.let {
                findChildViewUnder(it.x, it.y)?.let { child ->
                    return itemLongClickListener?.invoke(it, child, getChildAdapterPosition(child)) ?: false
                }
            }

            return false
        }

    }.apply {
        isLongClickable = true
    })

    addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(e)
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            //do nothing
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            //do nothing
        }

    })
}

inline fun View.onChildViewClick(
        childId: Int,
        eventX: Float,
        eventY: Float,
        clickAction: () -> Unit
) {
    //相对于父控件的触摸事件的坐标值
    val realEventX = eventX - left
    val realEventY = eventY - top
    findViewById<View>(childId)?.let { child->
        //子view的矩形
        val rect = Rect(child.left, child.top, child.right, child.bottom)
        val clickedChild = rect.contains(realEventX.toInt(), realEventY.toInt())
        Log.d("OnChildViewClick", "onChildViewClick: -->${clickedChild}")
        if (clickedChild) {
            clickAction.invoke()
        }
    }
}