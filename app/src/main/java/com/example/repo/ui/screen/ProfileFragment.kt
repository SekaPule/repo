package com.example.repo.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentProfileBinding
import com.example.repo.model.Friend
import com.example.repo.recycler.FriendsAdapter

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

        binding.friendsRV.layoutManager = LinearLayoutManager(requireContext())
        binding.friendsRV.adapter = FriendsAdapter(
            friends = listOf(
                Friend(id = 1, icon = R.drawable.avatar_1, fullName = "Дмитрий Валерьевич"),
                Friend(id = 2, icon = R.drawable.avatar_2, fullName = "Евгений Александров"),
                Friend(id = 3, icon = R.drawable.avatar_3, fullName = "Виктор Кузнецов"),
            )
        )
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}