package com.example.repo.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.R
import com.example.repo.databinding.CategoryLayoutBinding
import com.example.repo.model.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private val categories = listOf(
        Category(1, R.drawable.icon_kids, "Дети"),
        Category(2, R.drawable.icon_adult, "Взрослые"),
        Category(3, R.drawable.icon_elderly, "Пожилые"),
        Category(4, R.drawable.icon_animals, "Животные"),
        Category(5, R.drawable.icon_event, "Мероприятия"),
    )

    class CategoriesViewHolder(val binding: CategoryLayoutBinding) :
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