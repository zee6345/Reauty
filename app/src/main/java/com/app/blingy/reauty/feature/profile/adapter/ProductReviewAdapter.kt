package com.app.blingy.reauty.feature.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.blingy.reauty.core.domain.model.post.Post
import com.app.blingy.reauty.core.util.extension.setImageResString
import com.app.blingy.reauty.databinding.ItemPostGridviewBinding
import com.app.blingy.reauty.databinding.ItemUserProductReviewBinding


private val differCallback = object : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.hashCode() === newItem.hashCode()
    }

}

class ProductReviewAdapter(private val onItemFeedClickListener: OnItemPostsClickListener)
    : ListAdapter<Post, ProductReviewAdapter.GridViewViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewViewHolder {
        val binding = ItemUserProductReviewBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return GridViewViewHolder(binding, onItemFeedClickListener)
    }

    override fun onBindViewHolder(holder: GridViewViewHolder, position: Int) {

        val currentItem : Post = getItem(position)
        holder.bind(currentItem)


    }


    inner class GridViewViewHolder(private val binding:  ItemUserProductReviewBinding,
                                   private val onClickListener: OnItemPostsClickListener)
        : RecyclerView.ViewHolder(binding.root){

        private fun setImages(items : Post){
            binding.apply {
                val position = bindingAdapterPosition
                root.setOnClickListener { onClickListener.onItemViewClickedPost(
                    position
                ) }
                itemProductImgReview.setImageResString(items.postImageLink)
            }
        }

        fun bind(item: Post) {
            binding.apply {
                with(item) {
                    setImages(this)
                }
            }
        }

    }
    override fun getItemCount() = currentList.size

}