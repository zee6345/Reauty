package com.app.blingy.reauty.feature.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.blingy.reauty.databinding.FragmentSearchHostBinding
import com.app.blingy.reauty.feature.search.presentation.adapter.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchHostFragment : Fragment() {

    private var _binding: FragmentSearchHostBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPagerAdapter: SearchViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter =
            SearchViewPagerAdapter(parentFragmentManager, viewLifecycleOwner.lifecycle)
        binding.pager.adapter = viewPagerAdapter
        setupTabLayout()
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = viewPagerAdapter.titles[position]
        }.attach()
    }



}