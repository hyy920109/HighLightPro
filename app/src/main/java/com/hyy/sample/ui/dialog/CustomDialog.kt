package com.hyy.sample.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.core.graphics.toColorInt
import androidx.fragment.app.DialogFragment
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.OvalShape
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.sample.R
import com.hyy.sample.ui.util.AnimUtil

/**
 * CustomDialog class
 *
 * @author hsw created on 2021/4/12
 *
 */
class CustomDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.dialog_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        showHighlight()
    }

    private fun showHighlight() {
        HighlightPro.with(dialog?.window?.decorView as ViewGroup)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_1)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighlightShape(OvalShape(6f))
                    .setHighlightHorizontalPadding(12f.dp)
                    .setHighlightVerticalPadding(8f.dp)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(top = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_2)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(12f.dp)
                    .setHighlightVerticalPadding(8f.dp)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(top = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .build()
            }
            .setBackgroundColor("#80000000".toColorInt())
            .setOnShowCallback { index ->
                //do something
            }
            .setOnDismissCallback {
                //do something
            }
            .interceptBackPressed(true)
            .show()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val width = resources.displayMetrics.widthPixels
            val height = resources.displayMetrics.heightPixels
            setLayout((width*0.83f).toInt(), (height*0.8).toInt())

            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)
        }
    }

    companion object {
        const val TAG = "CustomDialog"
    }
}