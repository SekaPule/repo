package com.example.repo.presentation.profile.views

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentProfileBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.profile.views.component.EditImageDialog
import com.example.repo.presentation.profile.views.recycler.FriendsAdapter
import com.example.repo.presentation.profile.vm.ProfileScreenViewModel
import javax.inject.Inject

class ProfileScreenFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileScreenViewModel: ProfileScreenViewModel by viewModels()

    @Inject
    lateinit var friendsAdapter: FriendsAdapter

    @Inject
    lateinit var dialog: EditImageDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        configureRecyclerView()
        initAdapterContent()
        setFragmentResultListeners()
    }

    private fun configureRecyclerView() {
        binding.friendsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.friendsRV.adapter = friendsAdapter
    }

    private fun initAdapterContent() {
        friendsAdapter.friends = profileScreenViewModel.getFriends()
    }

    private fun setFragmentResultListeners() {
        parentFragmentManager.setFragmentResultListener(
            "requestKey",
            requireActivity()
        ) { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val uri = Uri.parse(bundle.getString("bundleKey"))
            updateProfilePhoto(uri = uri)
        }

        parentFragmentManager.setFragmentResultListener(
            "removeImageKey",
            requireActivity()
        ) { _, _ ->
            updateProfilePhoto(resId = R.drawable.ic_user_icon)
        }

        parentFragmentManager.setFragmentResultListener(
            "changeImageKey",
            requireActivity()
        ) { _, bundle ->
            val uri = Uri.parse(bundle.getString("bundleImageKey"))
            updateProfilePhoto(uri = uri)
            dialog.dialog?.cancel()
        }

        binding.imageView.setOnClickListener {
            showEditImageDialog()
        }
    }

    private fun showEditImageDialog() {
        dialog.show(parentFragmentManager, "DIALOG")
    }

    private fun updateProfilePhoto(uri: Uri){
        binding.imageView.setImageURI(uri)
    }

    private fun updateProfilePhoto(resId: Int){
        binding.imageView.setImageResource(resId)
    }


    companion object {

        @JvmStatic
        fun newInstance() = ProfileScreenFragment()
    }
}