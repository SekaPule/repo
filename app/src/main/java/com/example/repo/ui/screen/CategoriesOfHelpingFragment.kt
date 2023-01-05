package com.example.repo.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentCategoriesOfHelpingBinding
import com.example.repo.recycler.adapter.CategoriesAdapter
import com.example.repo.recycler.utils.MarginItemDecoration

class CategoriesOfHelpingFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesOfHelpingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesOfHelpingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesRV.apply {
            layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
            adapter = CategoriesAdapter()
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.spacing_xs).toInt(),
                    SPAN_COUNT
                )
            )
        }
    }

    companion object {
        private const val SPAN_COUNT = 2

        @JvmStatic
        fun newInstance() = CategoriesOfHelpingFragment()
    }
}