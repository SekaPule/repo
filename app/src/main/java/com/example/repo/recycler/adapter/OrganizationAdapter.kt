package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.OrganizationLayoutBinding
import com.example.repo.model.News

class OrganizationAdapter : ListAdapter<News, OrganizationAdapter.OrganizationViewHolder>(
    DifferCallback
) {

    class OrganizationViewHolder(val binding: OrganizationLayoutBinding) :
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

    object DifferCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }
}
