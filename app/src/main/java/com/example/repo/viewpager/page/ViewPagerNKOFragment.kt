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
import com.example.repo.databinding.FragmentViewPagerNKOBinding
import com.example.repo.model.News
import com.example.repo.recycler.adapter.OrganizationAdapter
import com.example.repo.ui.vm.SearchViewModel


class ViewPagerNKOFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerNKOBinding
    private val sharedViewModel: SearchViewModel by activityViewModels()
    private lateinit var dataProvider: DataProvider
    private val organizationAdapter by lazy { OrganizationAdapter() }
    private var organizationList: MutableList<News>? = null
    private var filteredOrganizationList: MutableList<News>? = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataProvider = DataProvider(requireContext())
        binding = FragmentViewPagerNKOBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nkoRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = organizationAdapter

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecorator)
        }

        if (organizationList == null) {
            organizationList = dataProvider.getNewsFromAssets() as MutableList<News>
            organizationAdapter.submitList(filteredOrganizationList)
        }

        sharedViewModel.searchText.observe(requireActivity()) { search ->
            if (organizationList != null) {
                filteredOrganizationList = organizationList!!.filter { organization ->
                    organization.organization!!.lowercase().contains(search)
                } as MutableList<News>?

                organizationAdapter.submitList(filteredOrganizationList)
            }
        }

    }

    override fun onPause() {
        super.onPause()

        organizationAdapter.submitList(filteredOrganizationList)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewPagerNKOFragment()
    }
}