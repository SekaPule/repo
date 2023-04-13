package com.example.repo.presentation.newslist.view.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.R
import com.example.repo.databinding.NewsItemLayoutBinding
import com.example.repo.presentation.details.views.DetailsScreenFragment
import com.example.search_feature.presentation.model.NewsView
import javax.inject.Inject


class NewsAdapter @Inject constructor() : ListAdapter<NewsView, NewsAdapter.NewsViewHolder>(DifferCallback) {
    private lateinit var context: Context

    class NewsViewHolder @Inject constructor(val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        return NewsViewHolder(
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = getItem(position)

        holder.binding.newsDate.text = newsItem.daysLeftText
        holder.binding.newsDescription.text = newsItem.titleDescription
        holder.binding.newsTitle.text = newsItem.title

        holder.binding.isNotCheckedLabel.isVisible = !newsItem.isChecked

        holder.binding.newsCard.setOnClickListener {
            newsItem.isChecked = true
            val bundle = bundleOf(
                NEWS_ITEM_KEY to newsItem
            )
            val fragment = DetailsScreenFragment.newInstance()
            fragment.arguments = bundle

            loadFragment(fragment = fragment)
        }
    }

    object DifferCallback : DiffUtil.ItemCallback<NewsView>() {
        override fun areItemsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NewsView, newItem: NewsView): Boolean {
            return oldItem == newItem
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val manager: FragmentManager = (context as AppCompatActivity).supportFragmentManager

        manager.commit {
            replace(R.id.screenContainer, fragment)
            addToBackStack("")
        }
    }

    companion object {
        private const val NEWS_ITEM_KEY = "newsItemKey"
    }
}