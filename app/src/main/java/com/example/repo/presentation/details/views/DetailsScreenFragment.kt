package com.example.repo.presentation.details.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentDetailsBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.base.model.NewsView
import com.example.repo.presentation.details.views.recycler.PhoneAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class DetailsScreenFragment : Fragment() {

    private lateinit var navigation: BottomNavigationView
    private lateinit var binding: FragmentDetailsBinding
    private var newsItem: NewsView? = null

    @Inject
    lateinit var phoneAdapter: PhoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        navigation = activity?.findViewById(R.id.navView)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        initParcelableData()
        configureToolBar()
        initDetailsViewContent()
        initAdapterContent()
        configureRecyclerView()
    }

    private fun initAdapterContent() {
        phoneAdapter.phoneNumbers = newsItem!!.phoneNumbers!!
    }

    private fun configureRecyclerView() {
        binding.apply {
            phoneRV.layoutManager = LinearLayoutManager(requireContext())
            phoneRV.adapter = phoneAdapter
        }
    }

    private fun initDetailsViewContent() {
        binding.apply {
            detailsTitle.text = newsItem!!.title
            detailsDate.text = newsItem!!.daysLeftText
            detailsOrganization.text = newsItem!!.organization
            detailsLocation.text = newsItem!!.location
            detailsDescription1.text = newsItem!!.description
            detailsDescription2.text = newsItem!!.subDescription
        }
    }

    private fun initParcelableData() {
        newsItem = arguments?.getParcelable(NEWS_ITEM_KEY)!!
    }

    private fun configureToolBar() {
        binding.toolBar.apply {
            title = newsItem?.title ?: TITLE_TEMPLATE
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        navigation.isGone = false
    }

    override fun onResume() {
        super.onResume()

        navigation.isGone = true
    }

    companion object {
        private const val TITLE_TEMPLATE = "Title"
        private const val NEWS_ITEM_KEY = "newsItemKey"

        @JvmStatic
        fun newInstance() = DetailsScreenFragment()
    }
}