package com.example.repo.ui.screen

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentProfileBinding
import com.example.repo.model.Friend
import com.example.repo.recycler.adapter.FriendsAdapter
import com.example.repo.ui.fragments.EditImageDialog

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("requestKey", requireActivity()) { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val uri = Uri.parse(bundle.getString("bundleKey"))
            binding.imageView.setImageURI(uri)
        }

        parentFragmentManager.setFragmentResultListener("removeImageKey", requireActivity()) { _, _ ->
            binding.imageView.setImageResource(R.drawable.ic_user_icon)
        }

        binding.friendsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.friendsRV.adapter = FriendsAdapter(
            friends = listOf(
                Friend(id = 1, icon = R.drawable.avatar_1, fullName = "Дмитрий Валерьевич"),
                Friend(id = 2, icon = R.drawable.avatar_2, fullName = "Евгений Александров"),
                Friend(id = 3, icon = R.drawable.avatar_3, fullName = "Виктор Кузнецов"),
            )
        )

        binding.imageView.setOnClickListener {
            val dialog = EditImageDialog()
            dialog.show(parentFragmentManager, null)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}