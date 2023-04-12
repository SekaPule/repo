package com.example.repo.presentation.categorieslist.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentCategoriesOfHelpingBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.categorieslist.views.recycler.CategoriesAdapter
import com.example.repo.presentation.categorieslist.views.recycler.CategoriesRecyclerItemDecoration
import com.example.repo.presentation.categorieslist.vm.CategoriesScreenViewModel
import javax.inject.Inject

class CategoriesScreenFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesOfHelpingBinding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val categoriesScreenViewModel: CategoriesScreenViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesOfHelpingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
        initAdapterContent()
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.categoriesRV.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = categoriesAdapter
            addItemDecoration(
                CategoriesRecyclerItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.spacing_xs),
                    SPAN_COUNT
                )
            )
        }
    }

    private fun initAdapterContent() {
        categoriesAdapter.categories = categoriesScreenViewModel.getCategories()
    }

    companion object {
        private const val SPAN_COUNT = 2

        @JvmStatic
        fun newInstance() = CategoriesScreenFragment()
    }
}