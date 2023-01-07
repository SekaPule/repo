package com.example.repo.viewpager.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.databinding.FragmentViewPagerEventBinding
import com.example.repo.recycler.adapter.OrganizationAdapter
import java.util.*

class ViewPagerEventFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerEventBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewPagerEventBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.eventRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = OrganizationAdapter().apply {
                organizations = getRandomStrings()
            }

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
            addItemDecoration(itemDecorator)
        }

    }

    override fun onPause() {
        super.onPause()
        binding.eventRV.apply {
            adapter = OrganizationAdapter().apply {
                organizations = getRandomStrings()
            }
        }
    }

    private fun getRandomString(): String {
        val random = Random()
        val bound = (LEFT_BORDER..RIGHT_BORDER).random()
        val sb = StringBuilder(bound)
        for (i in 0 until bound)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }

    private fun getRandomStrings(): List<String> {
        val bound = (LEFT_BORDER..RIGHT_BORDER).random()
        val result = mutableListOf<String>()
        for (i in 0 until bound) {
            result.add(getRandomString())
        }

        return result
    }

    companion object {
        private const val LEFT_BORDER = 5
        private const val RIGHT_BORDER = 42
        private const val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"

        @JvmStatic
        fun newInstance() = ViewPagerEventFragment()
    }
}