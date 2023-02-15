package com.example.repo.viewpager.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.data.DataProvider
import com.example.repo.databinding.FragmentViewPagerEventBinding
import com.example.repo.model.News
import com.example.repo.recycler.adapter.EventAdapter
import com.example.repo.ui.vm.SearchViewModel

class ViewPagerEventFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerEventBinding
    private val sharedViewModel: SearchViewModel by activityViewModels()
    private lateinit var dataProvider: DataProvider
    private val eventAdapter by lazy { EventAdapter() }
    private var eventList: MutableList<News>? = null
    private var filteredEventList: MutableList<News>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataProvider = DataProvider(requireContext())
        binding = FragmentViewPagerEventBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecorator)
        }

        if (eventList == null) {
            eventList = dataProvider.getNewsFromAssets() as MutableList<News>
            eventAdapter.submitList(filteredEventList)
        }

        sharedViewModel.searchText.observe(requireActivity()) { search ->
            if (eventList != null) {
                filteredEventList = eventList!!.filter { event ->
                    event.title!!.lowercase().contains(search)
                } as MutableList<News>?

                eventAdapter.submitList(filteredEventList)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        eventAdapter.submitList(filteredEventList)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ViewPagerEventFragment()
    }
}