package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.OrganizationLayoutBinding
import com.example.repo.model.News

class EventAdapter : ListAdapter<News, EventAdapter.EventViewHolder>(
    EventDifferCallback
) {

    class EventViewHolder(val binding: OrganizationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder(
            OrganizationLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)

        holder.binding.orgText.text = event.title

    }

    object EventDifferCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }
}
