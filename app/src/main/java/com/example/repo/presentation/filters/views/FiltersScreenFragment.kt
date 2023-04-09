package com.example.repo.presentation.filters.views

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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.repo.ExampleService
import com.example.repo.ExampleService.Companion.ACTION_MY_INTENT_SERVICE
import com.example.repo.R
import com.example.repo.databinding.FragmentFilterBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.domain.model.FilterList
import com.example.repo.presentation.base.mapper.FilterViewMapper
import com.example.repo.presentation.base.model.FilterView
import com.example.repo.presentation.filters.views.recycler.FilterAdapter
import com.example.repo.presentation.filters.vm.FilterScreenViewModel
import com.google.gson.Gson
import javax.inject.Inject


class FiltersScreenFragment : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    private val viewModel by activityViewModels<FilterScreenViewModel>()
    private var intentFilter = IntentFilter(ACTION_MY_INTENT_SERVICE)
    private var savedFilters: List<FilterView>? = null
    private var broadcastReceiver: BroadcastReceiver = MyBroadcastReceiver()

    @Inject
    lateinit var filterAdapter: FilterAdapter

    @Inject
    lateinit var filterViewMapper: FilterViewMapper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        configureToolBar()
        setViewListeners()
        configureRecyclerView()
        initFilters()
    }

    private fun initFilters() {
        savedFilters = viewModel.savedFilters
        if (savedFilters == null) {
            binding.progressBar.visibility = View.VISIBLE
            Intent(requireActivity(), ExampleService::class.java).also {
                requireActivity().startService(it)
            }
        } else {
            initAdapterData()
        }
    }

    private fun initAdapterData() {
        filterAdapter.filters = savedFilters!!
    }

    private fun configureRecyclerView() {
        binding.filterRV.apply {
            adapter = filterAdapter
        }
    }

    private fun setViewListeners() {
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

    private fun configureToolBar() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun registerBroadcastReceiver(
        broadcastReceiver: BroadcastReceiver,
        filter: IntentFilter
    ) {
        requireActivity().registerReceiver(broadcastReceiver, filter)
    }

    private fun unRegisterBroadcastReceiver(broadcastReceiver: BroadcastReceiver) {
        requireActivity().unregisterReceiver(broadcastReceiver)
    }

    override fun onStart() {
        super.onStart()

        registerBroadcastReceiver(broadcastReceiver = broadcastReceiver, filter = intentFilter)
    }

    override fun onStop() {
        super.onStop()
        if (filterAdapter.filters.isNotEmpty()) {
            viewModel.saveFilters(filterAdapter.filters)
        }

        unRegisterBroadcastReceiver(broadcastReceiver = broadcastReceiver)
    }

    companion object {
        private const val EXTRA_KEY_OUT = "EXTRA_OUT"
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = FiltersScreenFragment()
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val json = intent?.getStringExtra(EXTRA_KEY_OUT)
            savedFilters =
                viewModel.savedFilters ?: Gson().fromJson(
                    json,
                    FilterList::class.java
                ).filters.map { filter ->
                    filterViewMapper.mapFromDomainModel(type = filter)
                }
            binding.progressBar.visibility = View.GONE
            binding.filterRV.apply {
                adapter = filterAdapter.apply {
                    filters = savedFilters!!
                }
            }
        }
    }
}