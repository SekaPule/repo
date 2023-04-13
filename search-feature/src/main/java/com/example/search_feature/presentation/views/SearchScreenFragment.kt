package com.example.search_feature.presentation.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.example.search_feature.R
import com.example.search_feature.databinding.FragmentSearchBinding
import com.example.search_feature.di.SearchFeatureComponentViewModel
import com.example.search_feature.presentation.views.fragments.ViewPagerContainerFragment
import com.example.search_feature.presentation.views.fragments.ViewPagerNoEventFragment
import com.example.search_feature.presentation.views.viewpager.adapter.PagerAdapter
import com.example.search_feature.presentation.vm.SearchScreenViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import reactivecircus.flowbinding.android.widget.queryTextChanges
import javax.inject.Inject


class SearchScreenFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel: SearchScreenViewModel by activityViewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<SearchFeatureComponentViewModel>()
            .searchFeatureComponent
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter = PagerAdapter(fragmentActivity = requireActivity())
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, pos ->
            when (pos) {
                FIRST_POSITION -> {
                    tab.text = getString(R.string.tab_by_event)
                }
                else -> tab.text = getString(R.string.tab_by_nko)
            }
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            val queryFlow = binding.searchView.queryTextChanges()
                .debounce(SEARCH_TIMEOUT_MILLISECONDS)
                .map { text -> text.toString().lowercase().trim() }
                .distinctUntilChanged()
                .catch { e ->
                    e.localizedMessage?.let { Log.e("TAG", it) }
                }

            queryFlow.collect { text ->
                sharedViewModel.setSearchText(text = text)
                if (text.isBlank()) {
                    binding.pager.visibility = View.GONE
                    parentFragmentManager.commit {
                        replace(
                            binding.noEventLabel.id,
                            ViewPagerNoEventFragment.newInstance()
                        )
                    }
                } else {
                    binding.pager.visibility = View.VISIBLE
                    parentFragmentManager.commit {
                        replace(
                            binding.noEventLabel.id,
                            ViewPagerContainerFragment.newInstance()
                        )
                    }
                }
            }
        }

        binding.searchView.setQuery(sharedViewModel.searchText.value, false)

        if (binding.searchView.query.isBlank()) {
            binding.pager.visibility = View.GONE
            parentFragmentManager.commit {
                replace(
                    binding.noEventLabel.id,
                    ViewPagerNoEventFragment.newInstance()
                )
            }
        } else {
            binding.pager.visibility = View.VISIBLE
            parentFragmentManager.commit {
                replace(
                    binding.noEventLabel.id,
                    ViewPagerContainerFragment.newInstance()
                )
            }
        }

    }

    companion object {
        private const val SEARCH_TIMEOUT_MILLISECONDS: Long = 500
        private const val FIRST_POSITION = 0

        @JvmStatic
        fun newInstance() = SearchScreenFragment()
    }
}