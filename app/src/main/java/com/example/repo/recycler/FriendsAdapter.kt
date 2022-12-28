package com.example.repo.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.FriendLayoutBinding
import com.example.repo.model.Friend

class FriendsAdapter(
    private val friends: List<Friend>
) :
    RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    class FriendViewHolder(val binding: FriendLayoutBinding) : RecyclerView.ViewHolder(binding.root)

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
        holder.binding.friendIcon.setImageResource(friend.icon)

    }

    override fun getItemCount() = friends.size
}