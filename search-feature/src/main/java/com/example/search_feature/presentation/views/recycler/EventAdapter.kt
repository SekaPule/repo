package com.example.search_feature.presentation.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search_feature.databinding.OrganizationLayoutBinding
import com.example.search_feature.presentation.model.NewsView
import javax.inject.Inject

class EventAdapter @Inject constructor() : ListAdapter<NewsView, EventAdapter.EventViewHolder>(
    EventDifferCallback
) {

    class EventViewHolder@Inject constructor(val binding: OrganizationLayoutBinding) :
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

    object EventDifferCallback : DiffUtil.ItemCallback<NewsView>() {
        override fun areItemsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem == newItem
        }

    }
}
