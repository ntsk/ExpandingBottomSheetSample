package com.ntsk.expandingbottomsheetsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ntsk.expandingbottomsheetsample.databinding.FragmentExpandingBottomSheetBinding

class ExpandingBottomSheetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExpandingBottomSheetBinding.inflate(inflater, container, false)
        initSheetBehavior(binding)
        return binding.root
    }

    private fun initSheetBehavior(binding: FragmentExpandingBottomSheetBinding) {
        val behavior = BottomSheetBehavior.from(binding.sheetLayout)
        val maxTransitionX =
            (binding.sheetLayout.width - binding.sheetCollapsed.width).toFloat()

        when (behavior.state) {
            BottomSheetBehavior.STATE_EXPANDED -> {
                binding.sheetLayout.translationX = 0f
                binding.sheetCollapsed.alpha = 0f
                binding.sheetExpanded.alpha = 1f
            }
            BottomSheetBehavior.STATE_COLLAPSED -> {
                binding.sheetLayout.translationX = maxTransitionX
                binding.sheetCollapsed.alpha = 1f
                binding.sheetExpanded.alpha = 0f
            }
        }

        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val inverseOffset = 1.0f - slideOffset
                binding.sheetLayout.translationX = maxTransitionX * inverseOffset
                binding.sheetCollapsed.alpha = inverseOffset
                binding.sheetExpanded.alpha = slideOffset
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }
        })

        binding.sheetCollapsed.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}