package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.OrganizationLayoutBinding

class OrganizationAdapter : RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder>() {
    var organizations = listOf<String>()

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
        val organization = organizations[position]

        holder.binding.orgText.text = organization

    }

    override fun getItemCount() = organizations.size
}