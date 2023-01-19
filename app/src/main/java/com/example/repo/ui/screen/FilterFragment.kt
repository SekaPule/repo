package com.example.repo.ui.screen

import android.content.Context.MODE_PRIVATE
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
import com.example.repo.model.FilterItem
import com.example.repo.recycler.adapter.FilterAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private lateinit var dataProvider: DataProvider
    private val filterAdapter by lazy { FilterAdapter() }
    private var savedFilters: List<FilterItem>? = null

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

        val sharedPreferences =
            requireContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString(FILTERS_JSON_KEY, null)
        val type = object : TypeToken<List<FilterItem?>?>() {}.type
        savedFilters =
            gson.fromJson<List<FilterItem>>(json, type) ?: dataProvider.getFilterItemsFromAssets()

        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.filterRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = filterAdapter.apply {
                filters = savedFilters!!
            }
        }

        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.navigationConfirm -> {
                    setFragmentResult(
                        FILTER_RESULT_KEY,
                        bundleOf(FILTER_BUNDLE_KEY to filterAdapter.selectedFilters)
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

    override fun onStop() {
        super.onStop()
        if (filterAdapter.filters.isNotEmpty()) {
            val sharedPreferences =
                requireContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json: String = gson.toJson(filterAdapter.filters)
            editor.putString(FILTERS_JSON_KEY, json)
            editor.apply()
        }

    }

    companion object {
        private const val SHARED_PREFS = "shared preferences"
        private const val FILTERS_JSON_KEY = "filters"
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = FilterFragment()
    }
}