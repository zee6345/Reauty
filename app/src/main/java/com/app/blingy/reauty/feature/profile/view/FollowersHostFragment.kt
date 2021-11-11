package com.app.blingy.reauty.feature.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.blingy.reauty.R
import com.app.blingy.reauty.databinding.FragmentFollowersHostBinding
import com.app.blingy.reauty.databinding.FragmentSearchHostBinding
import com.app.blingy.reauty.feature.profile.adapter.FollowersViewPagerAdapter
import com.app.blingy.reauty.feature.search.presentation.adapter.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class FollowersHostFragment : Fragment() {
    private var _binding: FragmentFollowersHostBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: FollowersViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFollowersHostBinding.inflate(inflater,container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
    }

    private fun setUpUi(){
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter =
            FollowersViewPagerAdapter(parentFragmentManager, viewLifecycleOwner.lifecycle)
        binding.pager.adapter = viewPagerAdapter
        setupTabLayout()
    }
    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = viewPagerAdapter.titles[position]
        }.attach()
    }

}