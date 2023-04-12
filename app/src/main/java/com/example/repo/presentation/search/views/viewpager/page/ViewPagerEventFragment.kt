package com.example.repo.presentation.search.views.viewpager.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentViewPagerEventBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.base.model.NewsView
import com.example.repo.presentation.search.views.recycler.EventAdapter
import com.example.repo.presentation.search.vm.SearchScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewPagerEventFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerEventBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel: SearchScreenViewModel by activityViewModels{ viewModelFactory }

    @Inject
    lateinit var eventAdapter: EventAdapter

    private var eventList: List<NewsView>? = null
    private var filteredEventList: List<NewsView>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerEventBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        configureRecyclerView()
        initSearchData()
        setSearchObserver()
    }

    private fun setSearchObserver() {
        sharedViewModel.searchText.observe(requireActivity()) { search ->
            if (eventList != null) {
                filteredEventList = eventList!!.filter { event ->
                    event.title!!.lowercase().contains(search)
                }

                initAdapterData()
            }
        }
    }

    private fun initAdapterData() {
        eventAdapter.submitList(filteredEventList)
    }

    private fun initSearchData() {
        if(eventList==null) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                sharedViewModel.getNews().collect {
                    eventList = it
                }
            }
        }
    }

    private fun configureRecyclerView() {
        binding.eventRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecorator)
        }
    }

    override fun onResume() {
        super.onResume()

        initAdapterData()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewPagerEventFragment()
    }
}