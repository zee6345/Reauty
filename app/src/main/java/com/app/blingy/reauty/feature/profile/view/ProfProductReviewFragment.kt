package com.app.blingy.reauty.feature.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.post.DataSource
import com.app.blingy.reauty.databinding.FragmentProfProductReviewBinding
import com.app.blingy.reauty.databinding.FragmentProfileBinding
import com.app.blingy.reauty.feature.profile.adapter.GridViewAdapter
import com.app.blingy.reauty.feature.profile.adapter.OnItemPostsClickListener
import com.app.blingy.reauty.feature.profile.adapter.ProductReviewAdapter
import com.app.blingy.reauty.feature.profile.adapter.UserPostsAdapter
import com.app.blingy.reauty.feature.profile.model.toUiModelPost
import com.app.blingy.reauty.feature.profile.viewmodel.GetPostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfProductReviewFragment : Fragment() , OnItemPostsClickListener{

    private var _binding: FragmentProfProductReviewBinding? = null
    private val binding get() = _binding!!

    private val viewModel : GetPostsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentProfProductReviewBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment


        return binding.root

        }

    private fun createUserPostsGridAdapter() = ProductReviewAdapter(this)


    private fun setUpRecyclerView() = binding.productReviewRv.apply {
        val adapter1 = createUserPostsGridAdapter()



        adapter = adapter1

        lifecycleScope.launchWhenCreated {
            viewModel.postListLiveData.observe(viewLifecycleOwner){
                adapter1.submitList(it)
            }

        }
        layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    override fun onItemViewClickedPost(position: Int) {
        TODO("Not yet implemented")
    }
}

