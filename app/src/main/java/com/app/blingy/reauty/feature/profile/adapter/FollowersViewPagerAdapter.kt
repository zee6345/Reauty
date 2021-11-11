package com.app.blingy.reauty.feature.profile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.blingy.reauty.feature.profile.view.FollowersFragment
import com.app.blingy.reauty.feature.profile.view.FollowingFragment

class FollowersViewPagerAdapter(fragmentManager: FragmentManager,
                                lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val titles = listOf<String>(
        "Followers", "Following")

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowersFragment()
            else -> FollowingFragment()
        }
    }
}