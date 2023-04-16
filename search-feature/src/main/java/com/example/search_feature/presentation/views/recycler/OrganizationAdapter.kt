package com.example.search_feature.presentation.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search_feature.databinding.OrganizationLayoutBinding
import com.example.search_feature.presentation.model.NewsView
import javax.inject.Inject

class OrganizationAdapter @Inject constructor() : ListAdapter<NewsView, OrganizationAdapter.OrganizationViewHolder>(
    DifferCallback
) {

    class OrganizationViewHolder @Inject constructor(val binding: OrganizationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        return OrganizationViewHolder(
            OrganizationLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        val organization = getItem(position)

        holder.binding.orgText.text = organization.organization

    }

    object DifferCallback : DiffUtil.ItemCallback<NewsView>() {
        override fun areItemsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem == newItem
        }

    }
}
