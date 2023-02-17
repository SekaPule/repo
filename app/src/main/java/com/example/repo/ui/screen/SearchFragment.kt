package com.example.repo.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.repo.R
import com.example.repo.databinding.FragmentSearchBinding
import com.example.repo.ui.fragments.ViewPagerContainerFragment
import com.example.repo.ui.fragments.ViewPagerNoEventFragment
import com.example.repo.ui.vm.SearchViewModel
import com.example.repo.viewpager.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.jakewharton.rxbinding4.widget.queryTextChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val sharedViewModel: SearchViewModel by activityViewModels()
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

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

        val observable = binding.searchView.queryTextChanges()
            .debounce(SEARCH_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map { text -> text.toString().lowercase().trim() }
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())

        disposable = observable.subscribe(
            { text ->
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
            },
            {
                Log.e("TAG", "$it")
            })

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

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        private const val SEARCH_TIMEOUT_MILLISECONDS: Long = 500
        private const val FIRST_POSITION = 0

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}