package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.FilterItemLayoutBinding
import com.example.repo.model.FilterItem

class FilterAdapter : RecyclerView.Adapter<FilterAdapter.FilterItemViewHolder>() {
    var filters = listOf<FilterItem>()
    val selectedFilters = mutableListOf<String>()

    class FilterItemViewHolder(val binding: FilterItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterItemViewHolder {
        return FilterItemViewHolder(
            FilterItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilterItemViewHolder, position: Int) {
        val filter = filters[position]

        holder.binding.itemText.text = filter.name

        holder.binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                selectedFilters.add(filter.name)
            } else {
                selectedFilters.remove(filter.name)
            }
        }
    }

    override fun getItemCount() = filters.size
}