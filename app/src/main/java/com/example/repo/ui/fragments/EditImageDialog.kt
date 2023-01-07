package com.example.repo.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.repo.databinding.EditImageDialogLayoutBinding


class EditImageDialog : DialogFragment() {
    private lateinit var dialogBinding: EditImageDialogLayoutBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            dialogBinding = EditImageDialogLayoutBinding.inflate(inflater)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(dialogBinding.root)

            dialogBinding.deletePhotoItem.setOnClickListener {
                this.dialog?.cancel()
                setFragmentResult("removeImageKey", bundleOf())
            }

            dialogBinding.makePhotoItem.setOnClickListener {
                this.dialog?.cancel()
                val dialog = CameraDialog()
                dialog.show(parentFragmentManager, null)
            }

            // Add action buttons
            builder.create()
        }
    }
}