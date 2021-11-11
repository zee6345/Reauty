package com.app.blingy.reauty.feature.profile.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.blingy.reauty.feature.profile.view.PostProfFragment
import com.app.blingy.reauty.feature.profile.view.ProfProductReviewFragment

class ProfileViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        return when (position) {
            0 -> {
                PostProfFragment()
            }
            else -> {
                ProfProductReviewFragment()
            }
        }

    }

}