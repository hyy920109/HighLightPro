package com.hyy.guidesample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hyy.guidesample.R
import com.hyy.guidesample.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        mainViewModel =
                ViewModelProvider(this).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(inflater, container ,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardHighlight.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_highlightGuideFragment)
        }

        binding.btnPopup.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_popupFragment)
        }
    }

    companion object {
        fun create() : MainFragment {
            return MainFragment().apply {
                arguments = bundleOf(
                
                )
            }
        }
    }
}