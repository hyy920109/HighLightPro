package com.hyy.sample.ui.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.CircleShape
import com.hyy.highlightpro.shape.OvalShape
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.sample.R
import com.hyy.sample.databinding.FragmentHighlightMoreBinding
import com.hyy.sample.ui.util.AnimUtil
import com.hyy.sample.ui.util.PaintUtils

class HighlightMoreFragment : Fragment() {

    private lateinit var binding: FragmentHighlightMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHighlightMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.root.postDelayed({
            showHighlightSteps()
        }, 500)
    }

    private fun showHighlightSteps() {
        HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_second)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighlightShape(CircleShape().apply { setPaint(PaintUtils.getDashPaint()) })
                    .setHighlightHorizontalPadding(20f.dp)
                    .setHighlightVerticalPadding(20f.dp)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(top = 8.dp))
                    .setTipViewDisplayAnimation(AnimUtil.getScaleAnimation())
                    .build()
            }
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_third)
                    .setTipsViewId(R.layout.guide_step_third)
                    .setHighlightShape(OvalShape())
                    .setHighlightHorizontalPadding(12f.dp)
                    .setHighlightVerticalPadding(12f.dp)
                    .setConstraints(Constraints.BottomToTopOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(bottom = 6.dp))
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

    companion object {
        const val TAG = "HYY-HighlightGuideFragment"

        fun create(): HighlightMoreFragment {
            return HighlightMoreFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }

}