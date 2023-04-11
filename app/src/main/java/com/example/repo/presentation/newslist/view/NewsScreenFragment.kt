package com.example.repo.presentation.newslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentNewsBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.filters.views.FiltersScreenFragment
import com.example.repo.presentation.newslist.view.recycler.NewsAdapter
import com.example.repo.presentation.newslist.view.recycler.NewsMarginItemDecoration
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsScreenFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    @Inject
    lateinit var newsAdapter: NewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newsScreenViewModel: NewsScreenViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        configureRecyclerView()
        initDataContent()
        configureToolBar()
        setFragmentResultListeners()
    }

    private fun setFragmentResultListeners() {
        parentFragmentManager.setFragmentResultListener(
            FILTER_RESULT_KEY,
            requireActivity()
        ) { _, bundle ->
            val filters: List<String> = bundle.getParcelableArrayList(FILTER_BUNDLE_KEY)!!

            filterNewsList(filters = filters)
        }
    }

    private fun filterNewsList(filters: List<String>) {
        newsScreenViewModel.filterNews(filters = filters)
    }

    private fun configureToolBar() {
        binding.toolBar.title = resources.getString(R.string.news_title)
        binding.toolBar.setOnMenuItemClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.screenContainer,
                    FiltersScreenFragment.newInstance()
                )
                addToBackStack("")
            }

            true
        }
    }

    private fun initDataContent() {
        newsScreenViewModel.news.onEmpty {
            binding.progressBar.visibility = View.VISIBLE
            newsScreenViewModel.initNews()
            newsScreenViewModel.news.collect { newsList ->
                newsAdapter.submitList(newsList)
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun configureRecyclerView() {
        binding.newsRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            addItemDecoration(
                NewsMarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.spacing_xs)
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        newsScreenViewModel.setNotCheckedNewsCounter()

        viewLifecycleOwner.lifecycleScope.launch {
            newsScreenViewModel.news.collect { newsList ->
                newsAdapter.submitList(newsList)
            }
        }
    }

    companion object {
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = NewsScreenFragment()
    }
}