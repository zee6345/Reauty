package com.app.blingy.reauty.feature.profile.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.databinding.FragmentPostProfBinding
import com.app.blingy.reauty.feature.home.presentation.view.HomeFragmentDirections
import com.app.blingy.reauty.feature.profile.adapter.GridViewAdapter
import com.app.blingy.reauty.feature.profile.adapter.OnItemFeedClickListener
import com.app.blingy.reauty.feature.profile.adapter.OnItemPostsClickListener
import com.app.blingy.reauty.feature.profile.adapter.UserPostsAdapter
import com.app.blingy.reauty.feature.profile.model.UiModelPost
import com.app.blingy.reauty.feature.profile.model.toUiModelPost
import com.app.blingy.reauty.feature.profile.viewmodel.GetPostsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostProfFragment : Fragment(), OnItemFeedClickListener, OnItemPostsClickListener {
    private var _binding: FragmentPostProfBinding? = null
    private val binding get() = _binding!!
    private lateinit var postsAdapter: UserPostsAdapter
    private var isExpand: Boolean = false
    private var isFavourite: Boolean = false
    private var isLike: Boolean = false

    private val viewModel : GetPostsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPostProfBinding.inflate(inflater,container, false)


        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        setUpRecyclerView2()

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.rvMain.isVisible){
                    binding.profPostPhotosGrid.isVisible = true
                    binding.rvMain.isVisible = false
                }else{
                    findNavController().popBackStack()
                }


            }
        }
            )

    }

    private fun createUserPostsAdapter() = UserPostsAdapter(this)

    private fun createUserPostsGridAdapter() = GridViewAdapter(this)


    private fun setUpRecyclerView() = binding.profPostPhotosGrid.apply {
       val adapter1 = createUserPostsGridAdapter()



        adapter = adapter1

        lifecycleScope.launchWhenCreated {
            viewModel.postListLiveData.observe(viewLifecycleOwner){
                adapter1.submitList(it)
            }

        }
        layoutManager = GridLayoutManager(requireContext(), 3)
    }


    private fun setUpRecyclerView2() = binding.rvMain.apply {
        postsAdapter = createUserPostsAdapter()

        adapter = postsAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.postListLiveData.observe(viewLifecycleOwner){
                postsAdapter.submitList(it.map {
                    it.toUiModelPost()
                }
                )
            }

        }
        layoutManager = LinearLayoutManager(requireContext())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleItemExpand(currentItem: Int) {
        if (isExpand) {
            isExpand = !isExpand
            with(postsAdapter) {
                currentList[currentItem].isExpand = true
                notifyDataSetChanged()
            }
        } else {
            isExpand = !isExpand
            with(postsAdapter) {
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
            with(postsAdapter) {
                currentList[currentItem].isFavourite = true
                if (isLike) {
                    postsAdapter.currentList[currentItem].likes += 1
                }
                notifyDataSetChanged()
            }
        } else {
            isFavourite = !isFavourite
            isLike = !isLike
            with(postsAdapter) {
                currentList[currentItem].isFavourite = false
                if (!isLike) {
                    if (postsAdapter.currentList[currentItem].likes != 0) {
                        postsAdapter.currentList[currentItem].likes -= 1
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    private fun shareImage(item: UiModelPost) {
        val uri = Uri.parse(item.postImageLink)
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, uri)
            type = "image/jpeg"
        }
        startActivity(Intent.createChooser(shareIntent, "Share to..."))
    }

    private fun navigateToComment() {
        val action = PostProfFragmentDirections.actionPostProfFragmentToCommentFragment()
        findNavController()
            .navigate(action)
    }



    override fun onItemViewClicked(position: Int) {
        if(binding.rvMain.isVisible){
            handleItemExpand(position)
        }

    }

    override fun onAvatarClicked(position: Int) {
        TODO("Yet to implement On Avatar Clicked")
    }

    override fun onLikeClicked(position: Int) {
        handleLikeButtonClicked(position)
    }

    override fun onLikeExpandClicked(position: Int){
        handleLikeButtonClicked(position)
    }

    override fun onShareClicked(position: Int) {
        shareImage(postsAdapter.currentList[position])
    }

    override fun onShareExpandClicked(position: Int) {
        shareImage(postsAdapter.currentList[position])
    }

    override fun onCommentClicked(position: Int) {
        navigateToComment()
    }

    override fun onCommentExpandClicked(position: Int) {
        navigateToComment()
    }

    override fun onItemViewClickedPost(position: Int) {
        binding.profPostPhotosGrid.isVisible = false
        binding.rvMain.isVisible = true
        binding.rvMain.smoothScrollToPosition( position)

    }


}
