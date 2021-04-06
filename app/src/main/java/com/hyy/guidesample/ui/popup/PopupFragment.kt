package com.hyy.guidesample.ui.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyy.guidepro.GuidePro
import com.hyy.guidepro.parameter.Constraints
import com.hyy.guidepro.parameter.GuideParameter
import com.hyy.guidepro.parameter.MarginOffset
import com.hyy.guidepro.util.dp
import com.hyy.guidesample.R
import com.hyy.guidesample.databinding.FragmentPopupBinding

class PopupFragment : Fragment() {

    private lateinit var binding: FragmentPopupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.btnTipsBottom.setOnClickListener {
            showPopupWindowBottom()
        }
        binding.btnTipsTop.setOnClickListener {
            showPopupWindowTop()
        }
        binding.btnTipsLeft.setOnClickListener {
            showPopupWindowLeft()
        }
        binding.btnTipsRight.setOnClickListener {
            showPopupWindowRight()
        }
    }

    private fun showPopupWindowBottom() {
        GuidePro.with(this)
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_tips_bottom)
                    .setTipsViewId(R.layout.pop_tips_layout_bottom)
                    .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(end = (-2).dp))
                    .build()
            }
            .enableHighlight(false)//no highlight now is a popup window
            .interceptBackPressed(true)
            .show()
    }

    private fun showPopupWindowTop() {
        GuidePro.with(this)
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_tips_top)
                    .setTipsViewId(R.layout.pop_tips_layout_top)
                    .setConstraints(Constraints.BottomToTopOfHighlight + Constraints.EndToEndOfHighlight)
                    .setMarginOffset(MarginOffset(end = (-2).dp))
                    .build()
            }
            .enableHighlight(false)//no highlight now is a popup window
            .interceptBackPressed(true)
            .show()
    }

    private fun showPopupWindowLeft() {
        GuidePro.with(this)
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_tips_left)
                    .setTipsViewId(R.layout.pop_tips_layout_left)
                    .setConstraints(Constraints.EndToStartOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(top = (-2).dp))
                    .build()
            }
            .enableHighlight(false)//no highlight now is a popup window
            .interceptBackPressed(true)
            .show()
    }

    private fun showPopupWindowRight() {
        GuidePro.with(this)
            .setGuideViewParameter {
                GuideParameter.Builder()
                    .setHighLightViewId(R.id.btn_tips_right)
                    .setTipsViewId(R.layout.pop_tips_layout_right)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(top = (-2).dp))
                    .build()
            }
            .enableHighlight(false)//no highlight now is a popup window
            .interceptBackPressed(true)
            .show()
    }
}