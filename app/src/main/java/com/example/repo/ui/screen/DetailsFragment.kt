package com.example.repo.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentDetailsBinding
import com.example.repo.model.News
import com.example.repo.recycler.adapter.PhoneAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.datetime.*

class DetailsFragment : Fragment() {
    private lateinit var navigation: BottomNavigationView
    private lateinit var binding: FragmentDetailsBinding
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
        val newsItem: News? = arguments?.getParcelable("NEWS_ITEM_KEY")

        val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val newsDate = newsItem!!.date?.toLocalDate()
        val daysBetween = "осталось ${newsDate?.let { today.daysUntil(it) }} дней ($newsDate)"

        binding.toolBar.apply {
            title = newsItem.title ?: TITLE_TEMPLATE
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        binding.apply {
            detailsTitle.text = newsItem.title
            detailsDate.text = daysBetween
            detailsOrganization.text = newsItem.organization
            detailsLocation.text = newsItem.location
            detailsDescription1.text = newsItem.description
            detailsDescription2.text = newsItem.subDescription

            phoneRV.layoutManager = LinearLayoutManager(requireContext())
            phoneRV.adapter = PhoneAdapter().apply {
                phoneNumbers = newsItem.phoneNumbers as List<String>
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

        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}