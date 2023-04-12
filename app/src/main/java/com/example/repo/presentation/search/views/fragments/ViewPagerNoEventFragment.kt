package com.example.repo.presentation.search.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.repo.databinding.FragmentViewPagerNoEventBinding


class ViewPagerNoEventFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerNoEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerNoEventBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewPagerNoEventFragment()
    }
}