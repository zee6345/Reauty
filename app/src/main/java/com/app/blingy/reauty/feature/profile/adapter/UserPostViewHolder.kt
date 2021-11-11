package com.app.blingy.reauty.feature.profile.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.util.extension.setImageResString
import com.app.blingy.reauty.databinding.ItemFeedBinding
import com.app.blingy.reauty.feature.profile.model.UiModelPost

interface OnItemFeedClickListener {
    fun onItemViewClicked(position: Int)
    fun onAvatarClicked(position: Int)
    fun onLikeClicked(position: Int)
    fun onLikeExpandClicked(position: Int)
    fun onShareClicked(position: Int)
    fun onShareExpandClicked(position: Int)
    fun onCommentClicked(position: Int)
    fun onCommentExpandClicked(position: Int)
}


/**
 * viewholder for the userPost Item
 */
class UserPostViewHolder(
    private val binding: ItemFeedBinding,
    private val onClickListener: OnItemFeedClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private fun beforeExpandClickListener() {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            binding.apply {
                root.setOnClickListener {
                    onClickListener.onItemViewClicked(position)
                }
                imvLikeSocialFeed.setOnClickListener {
                    onClickListener.onLikeClicked(position)
                }
                imvCommentSocialFeed.setOnClickListener {
                    onClickListener.onCommentClicked(position)
                }
                imvShareSocialFeed.setOnClickListener {
                    onClickListener.onShareClicked(position)
                }
            }
        }
    }

    private fun afterExpandClickListener() {
        val position = bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            binding.apply {
                root.setOnClickListener {
                    onClickListener.onItemViewClicked(position)
                }
                imvLikeSocialFeedAfter.setOnClickListener {
                    onClickListener.onLikeExpandClicked(
                        position
                    )
                }
                imvCommentSocialFeedAfter.setOnClickListener {
                    onClickListener.onCommentExpandClicked(
                        position
                    )
                }
                imvShareSocialFeedAfter.setOnClickListener {
                    onClickListener.onShareExpandClicked(
                        position
                    )
                }
            }
        }
    }

    private fun expandRootView() {
        binding.apply {
            root.apply {
                // to set the root layout height match parent
                root.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                requestLayout()
            }
            // to show the after expand view
            containerBeforeExpand.visibility = View.GONE
            containerAfterExpand.visibility = View.VISIBLE
        }
    }

    private fun collapseRootView() {
        binding.apply {
            // to set the root layout height wrap content
            root.apply {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                requestLayout()
            }
            // to show the before expand view
            containerBeforeExpand.visibility = View.VISIBLE
            containerAfterExpand.visibility = View.GONE
        }
    }

    private fun setPostOwnerAvatar(item: UiModelPost) {
        binding.apply {
            // to show the user avatar is image url is not empty
            if (item.userProfileImage.isNotEmpty()) {
                imvUserProfilePhotoUserInfoFeed.setImageResString(item.userProfileImage)
            }
        }
    }

    private fun setPostOwnerAvatarAfter(item: UiModelPost) {
        binding.apply {
            // to show the user avatar is image url is not empty
            if (item.userProfileImage.isNotEmpty()) {
                imvUserProfilePhotoUserInfoFeedAfter.setImageResString(item.userProfileImage)
            }
        }
    }

    private fun setPostOwnerNameAndLikeCount(item: UiModelPost) {
        binding.apply {
            // to show the name of the owner of the post
            tvUserNameUserInfoFeed.text = item.postOwnerName
            tvLikeCountNumberUserInfoFeed.text = item.likes.toString()
        }
    }

    private fun setPostOwnerNameAndLikeCountAfter(item: UiModelPost) {
        binding.apply {
            // to show the name of the owner of the post
            tvUserNameUserInfoFeedAfter.text = item.postOwnerName
            tvLikeCountNumberUserInfoFeedAfter.text = item.likes.toString()
        }
    }

//    private fun setFavouriteImage(item: Post) {
//        binding.apply {
//            // to show the like image
//            if (item.isFavourite) {
//                imvLikeSocialFeed.setImageResource(R.drawable.ic_default_like_full)
//            } else {
//                imvLikeSocialFeed.setImageResource(R.drawable.ic_default_like_empty)
//            }
//        }
//    }

//    private fun setFavouriteImageAfter(item: Post) {
//        binding.apply {
//            // to show the like image
//            if (item.isFavourite) {
//                imvLikeSocialFeedAfter.setImageResource(R.drawable.ic_default_like_full)
//            } else {
//                imvLikeSocialFeedAfter.setImageResource(R.drawable.ic_default_like_empty)
//            }
//        }
//    }

    private fun setCommentImage(item: UiModelPost) {
        binding.apply {
            // to show the comment or comments
            if (item.comments.toInt() > 0) {
                imvCommentSocialFeed.setImageResource(R.drawable.ic_default_comments)
            } else {
                imvCommentSocialFeed.setImageResource(R.drawable.ic_default_comment)
            }
        }
    }

    private fun setCommentImageAfter(item: UiModelPost) {
        binding.apply {
            // to show the comment or comments
            if (item.comments.toInt() > 0) {
                imvCommentSocialFeedAfter.setImageResource(R.drawable.ic_default_comments)
            } else {
                imvCommentSocialFeedAfter.setImageResource(R.drawable.ic_default_comment)
            }
        }
    }

    fun bind(item: UiModelPost) {
        if (!item.isExpand) {
            // item isn't expand
            binding.apply {
                collapseRootView()
                with(item) {
                    setPostOwnerAvatar(this)
                    setPostOwnerNameAndLikeCount(this)
//                    setFavouriteImage(this)
                    setCommentImage(this)
                }
                // to show the post image
                imvFeedPhoto.setImageResString(item.postImageLink)
                // attach on click listener
                beforeExpandClickListener()
            }
        } else {
            // item is expand
            binding.apply {
                expandRootView()
                with(item) {
                    setPostOwnerAvatarAfter(this)
                    setPostOwnerNameAndLikeCountAfter(this)
//                    setFavouriteImageAfter(this)
                    setCommentImageAfter(this)
                }
                // to show the post image
                imvFeedPhotoAfter.setImageResString(item.postImageLink)
                // attach click listener
                afterExpandClickListener()
            }
        }
    }
}