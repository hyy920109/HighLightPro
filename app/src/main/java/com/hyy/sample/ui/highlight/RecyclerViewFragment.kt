package com.hyy.sample.ui.highlight

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.CircleShape
import com.hyy.highlightpro.shape.OvalShape
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.sample.R
import com.hyy.sample.databinding.FragmentRecyclerViewBinding
import com.hyy.sample.ui.adapter.RecyclerViewAdapter
import com.hyy.sample.ui.util.AnimUtil

class RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerViewBinding

    private val dataList by lazy {
        mutableListOf(
            R.mipmap.pic_1,
            R.mipmap.pic_2,
            R.mipmap.pic_3,
            R.mipmap.pic_4,
            R.mipmap.pic_5,
            R.mipmap.pic_6,
            R.mipmap.pic_7
        )
    }

    private val mAdapter by lazy {
        RecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            mAdapter.setList(dataList)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickListener { adapter, view, position ->
            showHighLight(view)
        }
    }

    private fun showHighLight(view: View) {
        HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightView(view)
                    .setTipsViewId(R.layout.hight_light_recycler_view)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(4f.dp)
                    .setHighlightVerticalPadding(4f.dp)
                    .setConstraints(Constraints.StartToStartOfHighlight + Constraints.BottomToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
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

        fun create(): RecyclerViewFragment {
            return RecyclerViewFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }
}