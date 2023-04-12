package com.example.repo.presentation.search.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.repo.databinding.FragmentViewPagerContainerBinding

class ViewPagerContainerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerContainerBinding.inflate(inflater)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewPagerContainerFragment()
    }
}