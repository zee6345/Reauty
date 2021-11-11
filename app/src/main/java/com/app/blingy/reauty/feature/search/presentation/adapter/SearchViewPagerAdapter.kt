package com.app.blingy.reauty.feature.search.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.blingy.reauty.feature.search.presentation.view.AccountsFragment
import com.app.blingy.reauty.feature.search.presentation.view.InfluencersFragment
import com.app.blingy.reauty.feature.search.presentation.view.PopularFragment
import com.app.blingy.reauty.feature.search.presentation.view.ProductsFragment
import com.app.blingy.reauty.feature.search.presentation.view.TagsFragment

class SearchViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val titles = listOf<String>(
        "Popular", "Accounts", "Influencers", "Tags", "Products"
    )

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> AccountsFragment()
            2 -> InfluencersFragment()
            3 -> TagsFragment()
            4 -> ProductsFragment()
            else -> PopularFragment()
        }
    }
}
