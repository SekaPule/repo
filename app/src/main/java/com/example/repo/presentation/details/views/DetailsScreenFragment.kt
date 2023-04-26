package com.example.repo.presentation.details.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.repo.R
import com.example.repo.databinding.FragmentDetailsBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.details.views.recycler.PhoneAdapter
import com.example.repo.presentation.details.vm.DetailsIntent
import com.example.repo.presentation.details.vm.DetailsScreenViewModel
import com.example.repo.presentation.theme.RepoTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class DetailsScreenFragment : Fragment() {

    private lateinit var navigation: BottomNavigationView
    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var phoneAdapter: PhoneAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailsViewModel: DetailsScreenViewModel by activityViewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        navigation = activity?.findViewById(R.id.navView)!!
        return ComposeView(requireContext()).apply {
            setContent {
                RepoTheme {
                    DetailsScreen(viewModel = detailsViewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        initParcelableData()
        setNavigationListeners()
    }

    private fun setNavigationListeners() {
        detailsViewModel.closeDetails.observe(viewLifecycleOwner) {
            if (it) {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun initParcelableData() {
        detailsViewModel.obtainIntent(DetailsIntent.InitDetailsDataIntent(newsView = arguments?.getParcelable(NEWS_ITEM_KEY)!!))
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
        private const val NEWS_ITEM_KEY = "newsItemKey"

        @JvmStatic
        fun newInstance() = DetailsScreenFragment()
    }
}