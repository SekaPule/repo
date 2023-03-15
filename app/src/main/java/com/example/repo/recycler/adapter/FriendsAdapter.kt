package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.example.repo.R
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
        holder.binding.friendIcon.load(friend.icon){
            diskCachePolicy(CachePolicy.DISABLED)
            error(R.drawable.ic_user_icon)
            placeholder(R.drawable.ic_user_icon)
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount() = friends.size
}