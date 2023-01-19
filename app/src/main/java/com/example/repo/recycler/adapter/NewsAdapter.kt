package com.example.repo.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repo.R
import com.example.repo.databinding.NewsItemLayoutBinding
import com.example.repo.model.News
import com.example.repo.ui.screen.DetailsFragment
import kotlinx.datetime.*


class NewsAdapter : ListAdapter<News, NewsAdapter.NewsViewHolder>(DifferCallback) {
    private lateinit var context: Context

    class NewsViewHolder(val binding: NewsItemLayoutBinding) :
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
        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val newsDate = newsItem!!.date?.toLocalDate()

        val daysBetween = "осталось ${newsDate?.let { today.daysUntil(it) }} дней ($newsDate)"

        holder.binding.newsDate.text = daysBetween
        holder.binding.newsDescription.text = newsItem.titleDescription
        holder.binding.newsTitle.text = newsItem.title

        holder.binding.newsCard.setOnClickListener {
            val bundle = bundleOf(
                NEWS_ITEM_KEY to newsItem
            )
            val fragment = DetailsFragment.newInstance()
            fragment.arguments = bundle

            loadFragment(fragment = fragment)
        }
    }

    object DifferCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
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