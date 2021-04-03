package com.hyy.guidesample.ui.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hyy.guidepro.GuidePro
import com.hyy.guidepro.parameter.Constraints
import com.hyy.guidepro.parameter.GuideParameter
import com.hyy.guidepro.shape.RectShape
import com.hyy.guidepro.util.dp
import com.hyy.guidesample.R
import com.hyy.guidesample.databinding.FragmentHighlightBinding

class HighlightGuideFragment : Fragment() {

    private lateinit var highlightViewModel: HighlightViewModel
    private lateinit var binding: FragmentHighlightBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        highlightViewModel =
                ViewModelProvider(this).get(HighlightViewModel::class.java)
        binding = FragmentHighlightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHelloWorld.doOnPreDraw {
            GuidePro.with(requireActivity())
                .setGuideViewParameter {
                    GuideParameter.Builder()
                        .setHighLightViewId(R.id.btn_hello_world)
                        .setTipsViewId(R.layout.guide_tips)
                        .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                        .setHighLightHorizontalPadding(8f.dp)
                        .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.StartToStartOfHighlight)
                        .build()
                }
                .show()
        }
    }

    companion object {
        fun create(): HighlightGuideFragment {
            return HighlightGuideFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }
}