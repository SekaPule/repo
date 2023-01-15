package com.example.repo.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.data.DataProvider
import com.example.repo.databinding.FragmentFilterBinding
import com.example.repo.recycler.adapter.FilterAdapter

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private lateinit var dataProvider: DataProvider
    private val filterAdapter by lazy { FilterAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataProvider = DataProvider(requireContext())
        binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.filterRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = filterAdapter.apply {
                filters = dataProvider.getFilterItemsFromAssets()
            }
        }

        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.navigationConfirm -> {
                    setFragmentResult(
                        "filterResultKey",
                        bundleOf("filterBundleKey" to filterAdapter.selectedFilters)
                    )
                    parentFragmentManager.popBackStack()

                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}