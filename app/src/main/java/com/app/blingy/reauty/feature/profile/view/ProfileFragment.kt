package com.app.blingy.reauty.feature.profile.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.blingy.reauty.R
import com.app.blingy.reauty.databinding.FragmentProfileBinding
import com.app.blingy.reauty.feature.profile.adapter.ProfileViewpagerAdapter
import com.app.blingy.reauty.feature.profile.viewmodel.GetPostsViewModel
import com.app.blingy.reauty.feature.profile.viewmodel.UserProfileViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2

    private val viewModel: UserProfileViewModel by viewModels()
    private val getPostVieModel : GetPostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        setHasOptionsMenu(true)

        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
        viewPager = binding.viewpager
        viewPager.adapter = ProfileViewpagerAdapter(this)


        setUpToolbarMenu()


        val scrollView = binding.nestedScrollView
        scrollView.isFillViewport = true


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayout


        setUpTab(tabLayout)
        navigateToPosts()
        binding.followersView.setOnClickListener {
            navigateToFollowers()
        }
        binding.FollowingView.setOnClickListener {
            navigateToFollowers()
        }

        lifecycleScope.launch{

            getPostVieModel.postSizeLiveData.observe(viewLifecycleOwner){ postSize ->
             binding.postsTV.text = postSize.toString()
            }

            viewModel.run {

                    userDataLiveData.observe(viewLifecycleOwner){user ->
                        Timber.d("User Called  ${user.userName}")
                      binding.profileUsername.text = user.userName

                        binding.shortBiography.text = user.bioText

                        if (user.bioText.isNotEmpty()){
                            binding.shortBiography.visibility = View.VISIBLE
                        }
                        Glide.with(this@ProfileFragment).load(user.profilePicLink).
                            override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                            .placeholder(R.drawable.ic_four_star_face).into(binding.profileImage)

                        Timber.d("profile imageLinks is  ${user.profilePicLink}")

                                   }

                        userDataIsLoading.observe(viewLifecycleOwner){state ->
                            if (state == true){
                                binding.progressBar.visibility = View.VISIBLE
                            }else{
                                binding.progressBar.visibility = View.GONE
                            }
                        }
            }
        }


    }

    private fun navigateToFollowers() {
        val directions= ProfileFragmentDirections.actionProfileFragmentToFollowersHostFragment()
        findNavController().navigate(directions)
    }

    private fun navigateToPosts() {
        binding.postView.setOnClickListener{
            val action = ProfileFragmentDirections.actionProfileFragmentToUserPostsFragment()
            findNavController().navigate(action)
        }
    }


    private fun setUpToolbarMenu() {
        binding.myToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.profileOptionFragment -> {

                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToProfileOptionFragment()
                    )
                }
            }
            onOptionsItemSelected(it)
        }
    }


    private fun setUpTab(tabLayout: TabLayout) {

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_mypost_android)
                }
                else -> {
                    tab.setIcon(R.drawable.ic_reautycrown_android)
                }
            }

        }.attach()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
