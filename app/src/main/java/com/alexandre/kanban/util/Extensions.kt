package com.alexandre.kanban.util

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.alexandre.kanban.R
import com.alexandre.kanban.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Fragment.initToolbar(toolbar: Toolbar) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title=""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {}
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.bottomSheetDialog)
    val binding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater, null, false)

    binding.textViewTitle.text = getText(titleDialog?: R.string.text_title_warning)
    binding.textViewMessage.text = message
    binding.btnOk.text = getText(titleButton?: R.string.text_button_warning)
    binding.btnOk.setOnClickListener {
        onClick()
        bottomSheetDialog.dismiss()
    }

    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()

}