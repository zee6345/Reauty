package com.app.blingy.reauty.feature.chat.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.blingy.reauty.feature.chat.FriendsFragment
import com.app.blingy.reauty.feature.chat.PartnerFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() =  2

    override fun createFragment(position: Int): Fragment {
      return  when(position){
            0 ->{
                FriendsFragment()
            }
            else ->{
                PartnerFragment()
            }
        }
    }
}