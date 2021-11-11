package com.app.blingy.reauty.feature.home.presentation.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.feed.BeautyTipType
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.databinding.FragmentHomeBinding
import com.app.blingy.reauty.feature.home.domain.model.UiModelFeed
import com.app.blingy.reauty.feature.home.presentation.adapter.BeautyTipAdapter
import com.app.blingy.reauty.feature.home.presentation.adapter.FeedAdapter
import com.app.blingy.reauty.feature.home.presentation.adapter.viewholder.OnItemFeedClickListener
import com.app.blingy.reauty.feature.home.presentation.contract.HomeContract
import com.app.blingy.reauty.feature.home.presentation.viewmodel.HomeViewModel
import com.app.blingy.reauty.feature.profile.adapter.UserPostsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), OnItemFeedClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var beautyTipAdapter: BeautyTipAdapter
    private lateinit var feedAdapter: FeedAdapter

    private var isExpand: Boolean = false
    private var isFavourite: Boolean = false
    private var isLike: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        initObserver()
        initErrorObserver()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.rvTips?.adapter = null
        _binding?.rvMain?.adapter = null
        _binding = null
    }

    override fun onItemViewClicked(position: Int) {
        handleItemExpand(position)
    }

    override fun onAvatarClicked(position: Int) {
        val postOwnerId = feedAdapter.currentList[position].postOwnerId
        lifecycleScope.launch {
            viewModel.setEvent(HomeContract.HomeEvent.OnAvatarClicked(postOwnerId))
        }
    }

    override fun onLikeClicked(position: Int) {
        handleLikeButtonClicked(position)
    }

    override fun onLikeExpandClicked(position: Int) {
        handleLikeButtonClicked(position)
    }

    override fun onShareClicked(position: Int) {
        shareImage(feedAdapter.currentList[position])
    }

    override fun onShareExpandClicked(position: Int) {
        shareImage(feedAdapter.currentList[position])
    }

    override fun onCommentClicked(position: Int) {
        navigateToComment()
    }

    override fun onCommentExpandClicked(position: Int) {
        navigateToComment()
    }

    override fun onViewAllCommentClicked(position: Int) {
        navigateToComment()
    }

    private fun setupUi() {
        setupBeautyTipRecyclerView()
        setupFeedRecyclerView()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                when (uiState.isLoading) {
                    true -> binding.progress.isVisible = true
                    false -> binding.progress.isVisible = false
                }.exhaustive
                when (uiState.beautyTips.isNotEmpty()) {
                    true -> updateBeautyTipUiState(uiState, beautyTipAdapter)
                    false -> showEmptyDataUi()
                }.exhaustive
                when (uiState.feedList.isNotEmpty()) {
                    true -> updateFeedUiState(uiState, feedAdapter)
                    false -> showEmptyDataUi()
                }.exhaustive
            }
        }
    }

    private fun initErrorObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is HomeContract.HomeSideEffect.ShowSnackBar -> showSnackBar(sideEffect.message)
                }
            }
        }
    }

    private fun setupBeautyTipRecyclerView() {
        beautyTipAdapter = createBeautyTipAdapter()
        val beautyTipLayoutManager = LinearLayoutManager(requireContext()).also {
            it.orientation = LinearLayoutManager.HORIZONTAL
        }
        binding.rvTips.apply {
            adapter = beautyTipAdapter
            layoutManager = beautyTipLayoutManager
            setHasFixedSize(true)
        }
        sendGetBeautyTipEvent()
    }

    private fun createBeautyTipAdapter(): BeautyTipAdapter {
        return BeautyTipAdapter {
            Timber.d("setBeautyTipItemClickListener: item: $it")
            var safeArgs: BeautyTipType = BeautyTipType.ACNE_PRONE
            when (BeautyTipType.values()[it]) {
                BeautyTipType.ACNE_PRONE -> safeArgs = BeautyTipType.ACNE_PRONE
                BeautyTipType.ANTI_AGING -> safeArgs = BeautyTipType.ANTI_AGING
                BeautyTipType.CLEAN_BEAUTY -> safeArgs = BeautyTipType.CLEAN_BEAUTY
                BeautyTipType.CRUELTY_FREE -> safeArgs = BeautyTipType.CRUELTY_FREE
                BeautyTipType.DARK_CIRCLES -> safeArgs = BeautyTipType.DARK_CIRCLES
                BeautyTipType.DEHYDRATED -> safeArgs = BeautyTipType.DEHYDRATED
                BeautyTipType.DULL_SKIN -> safeArgs = BeautyTipType.DULL_SKIN
                BeautyTipType.LARGE_PORES -> safeArgs = BeautyTipType.LARGE_PORES
                BeautyTipType.MENS_SKIN -> safeArgs = BeautyTipType.MENS_SKIN
                BeautyTipType.NATURAL_BEAUTY -> safeArgs = BeautyTipType.NATURAL_BEAUTY
                BeautyTipType.REDNESS -> safeArgs = BeautyTipType.REDNESS
                BeautyTipType.VEGAN_BEAUTY -> safeArgs = BeautyTipType.VEGAN_BEAUTY
            }.exhaustive
            val action =
                HomeFragmentDirections.actionHomeFragmentToBeautyTipFragment(safeArgs)
            findNavController()
                .navigate(action)
        }
    }

    private fun sendGetBeautyTipEvent() {
        lifecycleScope.launch {
            viewModel.setEvent(HomeContract.HomeEvent.GetBeautyTips)
        }
    }

    private fun updateBeautyTipUiState(
        uiState: HomeContract.HomeViewState, adapter: BeautyTipAdapter
    ) {
        adapter.submitList(uiState.beautyTips)
    }

    private fun setupFeedRecyclerView() {
        feedAdapter = createFeedAdapter()
        val feedLayoutManager = LinearLayoutManager(requireContext())
        binding.rvMain.apply {
            adapter = feedAdapter
            layoutManager = feedLayoutManager
            setHasFixedSize(true)
        }
        sendGetFeedsEvent()
    }


    private fun createFeedAdapter() = FeedAdapter(this)

    @SuppressLint("NotifyDataSetChanged")
    private fun handleItemExpand(currentItem: Int) {
        if (isExpand) {
            isExpand = !isExpand
            with(feedAdapter) {
                currentList[currentItem].isExpand = true
                notifyDataSetChanged()
            }
        } else {
            isExpand = !isExpand
            with(feedAdapter) {
                currentList[currentItem].isExpand = false
                notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleLikeButtonClicked(currentItem: Int) {
        if (isFavourite) {
            isFavourite = !isFavourite
            isLike = !isLike
            with(feedAdapter) {
                currentList[currentItem].isFavourite = true
                if (isLike) {
                    feedAdapter.currentList[currentItem].likes += 1
                }
                notifyDataSetChanged()
            }
        } else {
            isFavourite = !isFavourite
            isLike = !isLike
            with(feedAdapter) {
                currentList[currentItem].isFavourite = false
                if (!isLike) {
                    if (feedAdapter.currentList[currentItem].likes != 0) {
                        feedAdapter.currentList[currentItem].likes -= 1
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun shareImage(item: UiModelFeed) {
        val uri = Uri.parse(item.postImageLink)
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpeg"
        }
        startActivity(Intent.createChooser(shareIntent, "Share to..."))
    }

    private fun navigateToComment() {
        val action = HomeFragmentDirections.actionHomeFragmentToCommentFragment()
        findNavController()
            .navigate(action)
    }

    private fun navigateToSearchHost() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchHostFragment()
        findNavController()
            .navigate(action)
    }

    private fun sendGetFeedsEvent() {
        lifecycleScope.launch {
            viewModel.setEvent(HomeContract.HomeEvent.GetFeeds)
        }
    }

    private fun updateFeedUiState(
        uiState: HomeContract.HomeViewState, adapter: FeedAdapter
    ) {
        adapter.submitList(uiState.feedList)
    }

    private fun showSnackBar(value: String) {
        binding.root.shortSnackBar(message = value)
    }

    private fun showEmptyDataUi() {}

}