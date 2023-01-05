package com.example.repo.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.repo.R
import com.example.repo.databinding.EditImageDialogLayoutBinding
import com.example.repo.databinding.FragmentProfileBinding


class EditImageDialog(private val profileBinding: FragmentProfileBinding) : DialogFragment() {
    private lateinit var dialogBinding: EditImageDialogLayoutBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            dialogBinding = EditImageDialogLayoutBinding.inflate(inflater)

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(dialogBinding.root)

            dialogBinding.deletePhotoItem.setOnClickListener {
                this.dialog?.cancel()
                profileBinding.imageView.setImageResource(R.drawable.ic_user_icon)
            }

            dialogBinding.makePhotoItem.setOnClickListener {
                this.dialog?.cancel()
                val dialog = CameraDialog(profileBinding = profileBinding)
                dialog.show(parentFragmentManager, null)
            }

            // Add action buttons
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}