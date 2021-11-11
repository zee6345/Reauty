package com.app.blingy.reauty.feature.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.app.blingy.reauty.R
import com.app.blingy.reauty.databinding.FragmentChatBinding
import com.app.blingy.reauty.feature.chat.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager2 : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater, container, false)


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager2 = binding.chatViewpager
        viewPager2.adapter = ViewPagerAdapter(this)
        val tabLayout = binding.chatTabLayout

        setupTabLayout(tabLayout)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setupTabLayout(tabLayout: TabLayout ){

        TabLayoutMediator(tabLayout,viewPager2){tab,position ->

            when(position){
                0 ->{
                    tab.setIcon(R.drawable.ic_mypost_android)
                }
                else ->{
                    tab.setIcon(R.drawable.ic_mypost_android)
                }
            }


        }

    }

}