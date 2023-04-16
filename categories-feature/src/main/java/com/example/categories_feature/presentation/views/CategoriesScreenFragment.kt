package com.example.categories_feature.presentation.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.categories_feature.R
import com.example.categories_feature.databinding.FragmentCategoriesScreenBinding
import com.example.categories_feature.di.CategoriesFeatureComponentViewModel
import com.example.categories_feature.presentation.views.recycler.CategoriesAdapter
import com.example.categories_feature.presentation.views.recycler.CategoriesRecyclerItemDecoration
import com.example.categories_feature.presentation.vm.CategoriesScreenViewModel
import javax.inject.Inject

class CategoriesScreenFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesScreenBinding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val categoriesScreenViewModel: CategoriesScreenViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<CategoriesFeatureComponentViewModel>()
            .categoriesFeatureComponent
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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