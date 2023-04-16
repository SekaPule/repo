package com.example.search_feature.presentation.views.viewpager.page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.search_feature.R
import com.example.search_feature.databinding.FragmentViewPagerNKOBinding
import com.example.search_feature.di.SearchFeatureComponentViewModel
import com.example.search_feature.presentation.model.NewsView
import com.example.search_feature.presentation.views.recycler.EventAdapter
import com.example.search_feature.presentation.vm.SearchScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ViewPagerNKOFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerNKOBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel: SearchScreenViewModel by activityViewModels{ viewModelFactory }

    @Inject
    lateinit var organizationAdapter: EventAdapter

    private var organizationList: List<NewsView>? = null
    private var filteredOrganizationList: List<NewsView>? = mutableListOf()

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
        binding = FragmentViewPagerNKOBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        initSearchData()
        setSearchObserver()
    }

    private fun setSearchObserver() {
        sharedViewModel.searchText.observe(requireActivity()) { search ->
            if (organizationList != null) {
                filteredOrganizationList = organizationList!!.filter { event ->
                    event.title!!.lowercase().contains(search)
                }

                initAdapterData()
            }
        }
    }

    private fun initAdapterData() {
        organizationAdapter.submitList(filteredOrganizationList)
    }

    private fun initSearchData() {
        if(organizationList==null) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                sharedViewModel.getNews().collect {
                    organizationList = it
                }
            }
        }
    }

    private fun configureRecyclerView() {
        binding.nkoRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = organizationAdapter

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecorator)
        }
    }

    override fun onPause() {
        super.onPause()

        initAdapterData()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewPagerNKOFragment()
    }
}