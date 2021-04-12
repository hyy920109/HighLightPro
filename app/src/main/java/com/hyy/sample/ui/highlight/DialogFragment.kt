package com.hyy.sample.ui.highlight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyy.sample.databinding.FragmentDialogBinding
import com.hyy.sample.ui.dialog.CustomDialog

/**
 * DialogFragment class
 *
 * @author LX created on 2021/4/12
 *
 */
class DialogFragment : Fragment() {

    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() {
        binding.btnShowDialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        CustomDialog().show(childFragmentManager, CustomDialog.TAG)
    }
}