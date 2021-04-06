package com.hyy.guidesample.ui.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hyy.guidepro.GuidePro
import com.hyy.guidepro.parameter.Constraints
import com.hyy.guidepro.parameter.GuideParameter
import com.hyy.guidepro.parameter.MarginOffset
import com.hyy.guidepro.shape.RectShape
import com.hyy.guidepro.util.dp
import com.hyy.guidesample.R
import com.hyy.guidesample.databinding.FragmentHighlightMoreBinding

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
        GuidePro.with(this)
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
            }
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_step_second)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(top = 8.dp))
                    .build()
            }
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_step_third)
                    .setTipsViewId(R.layout.guide_step_third)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.BottomToTopOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(bottom = 6.dp))
                    .build()
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