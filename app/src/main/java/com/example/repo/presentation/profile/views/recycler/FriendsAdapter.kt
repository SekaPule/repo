package com.example.repo.presentation.profile.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.repo.R
import com.example.repo.databinding.FriendLayoutBinding
import com.example.repo.domain.model.Friend
import javax.inject.Inject

class FriendsAdapter @Inject constructor() : RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    lateinit var friends: List<Friend>

    class FriendViewHolder @Inject constructor(val binding: FriendLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        return FriendViewHolder(
            FriendLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friend = friends[position]

        holder.binding.friendFullName.text = friend.fullName
        holder.binding.friendIcon.load(friend.icon) {
            diskCachePolicy(CachePolicy.DISABLED)
            error(R.drawable.ic_user_icon)
            placeholder(R.drawable.ic_user_icon)
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount() = friends.size
}