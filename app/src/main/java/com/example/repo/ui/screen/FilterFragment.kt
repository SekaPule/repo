package com.example.repo.ui.screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.ExampleService
import com.example.repo.ExampleService.Companion.ACTION_MY_INTENT_SERVICE
import com.example.repo.R
import com.example.repo.data.DataProvider
import com.example.repo.databinding.FragmentFilterBinding
import com.example.repo.model.FilterItem
import com.example.repo.model.FilterList
import com.example.repo.recycler.adapter.FilterAdapter
import com.example.repo.ui.vm.FilterViewModel
import com.google.gson.Gson


class FilterFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private lateinit var dataProvider: DataProvider
    private val filterAdapter by lazy { FilterAdapter() }
    private lateinit var viewModel: FilterViewModel
    private var broadcastReceiver: BroadcastReceiver
    private var filter = IntentFilter(ACTION_MY_INTENT_SERVICE)


    private var savedFilters: List<FilterItem>? = null

    init {
        broadcastReceiver = MyBroadcastReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[FilterViewModel::class.java]
        dataProvider = DataProvider(requireContext())
        binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedFilters = viewModel.savedFilters
        if (savedFilters == null) {
            binding.progressBar.visibility = View.VISIBLE
            Intent(requireActivity(), ExampleService::class.java).also {
                requireActivity().startService(it)
            }
        } else {
            binding.filterRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = filterAdapter.apply {
                    filters = savedFilters!!
                }
            }
        }

        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
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

    override fun onStart() {
        super.onStart()
        requireActivity().registerReceiver(broadcastReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        if (filterAdapter.filters.isNotEmpty()) {
            viewModel.saveFilters(filterAdapter.filters)
        }
        requireActivity().unregisterReceiver(broadcastReceiver)
    }

    companion object {
        private const val EXTRA_KEY_OUT = "EXTRA_OUT"
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = FilterFragment()
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val json = intent?.getStringExtra(EXTRA_KEY_OUT)
            savedFilters =
                viewModel.savedFilters ?: Gson().fromJson(json, FilterList::class.java).filters
            binding.progressBar.visibility = View.GONE
            binding.filterRV.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = filterAdapter.apply {
                    filters = savedFilters!!
                }
            }
        }
    }
}