package com.hyy.sample.ui.dialog

import android.os.Bundle
import android.view.*
import androidx.core.graphics.toColorInt
import androidx.fragment.app.DialogFragment
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.CircleShape
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.sample.R

/**
 * CustomDialog class
 *
 * @author LX created on 2021/4/12
 *
 */
class CustomDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        showHighlight(view)
    }

    private fun showHighlight(view: View) {
        HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_1)
                    .setTipsViewId(R.layout.layout_dialog_tips)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(4f.dp)
                    .setHighlightVerticalPadding(4f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_2)
                    .setTipsViewId(R.layout.layout_dialog_tips)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(4f.dp)
                    .setHighlightVerticalPadding(4f.dp)
                    .setConstraints(Constraints.StartToStartOfHighlight + Constraints.BottomToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
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
        val window = dialog?.window
        val params = window?.attributes
//      params.dimAmount = getDimAmount()
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = Gravity.CENTER
        window?.attributes = params
    }


    companion object {
        const val TAG = "CustomDialog"
    }
}