package com.hyy.sample.ui.highlight

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyy.highlightpro.HighlightPro
import com.hyy.highlightpro.parameter.Constraints
import com.hyy.highlightpro.parameter.HighlightParameter
import com.hyy.highlightpro.parameter.MarginOffset
import com.hyy.highlightpro.shape.RectShape
import com.hyy.highlightpro.util.dp
import com.hyy.sample.R
import com.hyy.sample.databinding.FragmentRecyclerViewBinding
import com.hyy.sample.ui.adapter.RecyclerViewAdapter
import com.hyy.sample.ui.util.AnimUtil
import com.hyy.sample.ui.util.setOnItemClickAndLongClickedListener

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

    private val adapter by lazy {
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
        Toast.makeText(requireContext(), getString(R.string.click_item_show_highlight), Toast.LENGTH_SHORT).show();
    }

    private fun initView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            this@RecyclerViewFragment.adapter.setList(dataList)
            adapter = this@RecyclerViewFragment.adapter
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val position = parent.getChildAdapterPosition(view)
                    if (position == 0) {
                        outRect.top = 12.dp
                        outRect.bottom = 12.dp
                    }else {
                        outRect.bottom = 12.dp
                    }
                    outRect.left = 12.dp
                    outRect.right = 12.dp
                }
            })
        }

//        adapter.setOnItemClickListener { adapter, view, position ->
//            showHighLight(view)
//        }

        binding.recyclerView.setOnItemClickAndLongClickedListener(itemClickListener = {e, view, position ->
            showHighLight(view)
            return@setOnItemClickAndLongClickedListener true
        }, { e, view, position ->

            showLongHighLight(e)
            return@setOnItemClickAndLongClickedListener true
        })
    }

    private fun showLongHighLight(event: MotionEvent) {

        val menuView = LayoutInflater.from(requireContext()).inflate(R.layout.menu_list_item, null)
        menuView.findViewById<View>(R.id.btn_report).setOnClickListener {

        }
        menuView.findViewById<View>(R.id.btn_black_list).setOnClickListener {

        }
        HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setTipsView(menuView)
                    .offsetX(event.rawX.toInt())
                    .offsetY(event.rawY.toInt())
                    .build()
            }
            .needAnchorTipView(false)
            .enableHighlight(false)
            .setBackgroundColor("#00000000".toColorInt())
            .setOnShowCallback { index ->
                //do something
            }
            .setOnDismissCallback {
                //do something
            }
            .interceptBackPressed(true)
            .show()
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
        const val TAG = "HYY-RecyclerViewFragment"

        fun create(): RecyclerViewFragment {
            return RecyclerViewFragment().apply {
                arguments = bundleOf(

                )
            }
        }
    }
}