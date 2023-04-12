package com.example.repo.presentation.categorieslist.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.databinding.CategoryLayoutBinding
import com.example.repo.domain.model.Category
import javax.inject.Inject

class CategoriesAdapter @Inject constructor() : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    lateinit var categories: List<Category>

    class CategoriesViewHolder @Inject constructor(val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]

        holder.binding.categoryTitle.text = category.title
        holder.binding.categoryIcon.setImageResource(category.icon)

    }

    override fun getItemCount() = categories.size
}