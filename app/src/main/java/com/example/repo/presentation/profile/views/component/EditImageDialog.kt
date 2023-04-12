package com.example.repo.presentation.profile.views.component

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.repo.databinding.EditImageDialogLayoutBinding
import javax.inject.Inject


class EditImageDialog @Inject constructor() : DialogFragment() {
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

            val resultLauncher =
                registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                    parentFragmentManager.setFragmentResult(
                        "changeImageKey",
                        bundleOf("bundleImageKey" to uri.toString())
                    )
                }

            dialogBinding.chosePhotoItem.setOnClickListener {
                resultLauncher.launch("image/*")
            }

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