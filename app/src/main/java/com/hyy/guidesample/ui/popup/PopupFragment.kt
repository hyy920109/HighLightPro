package com.hyy.guidesample.ui.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hyy.guidesample.databinding.FragmentPopupBinding

class PopupFragment : Fragment() {

    private lateinit var popupViewModel: PopupViewModel
    private lateinit var binding: FragmentPopupBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        popupViewModel =
                ViewModelProvider(this).get(PopupViewModel::class.java)
        binding = FragmentPopupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}