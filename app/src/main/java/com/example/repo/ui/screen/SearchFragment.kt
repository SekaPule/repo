package com.example.repo.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.repo.R
import com.example.repo.databinding.FragmentSearchBinding
import com.example.repo.viewpager.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
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
    }

    companion object {
        private const val FIRST_POSITION = 0

        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}