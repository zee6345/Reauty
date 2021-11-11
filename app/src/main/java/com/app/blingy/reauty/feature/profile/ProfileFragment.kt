package com.app.blingy.reauty.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.databinding.FragmentProfileBinding
import com.app.blingy.reauty.feature.profile.adapter.ProfileViewpagerAdapter
import com.app.blingy.reauty.feature.profile.viewmodel.FirebaseCallback
import com.app.blingy.reauty.feature.profile.viewstate.Response
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    @Inject
    lateinit var database: FirebaseDatabase

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2


//    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewPager = binding.viewpager
        viewPager.adapter = ProfileViewpagerAdapter(this)
        val tabLayout = binding.tabLayout
        setUpTab(tabLayout)
         showUserDetails()
//        getResponseUsingCallback()


        return binding.root
    }

//    private fun getResponseUsingCallback() {
//        viewModel.getResponseUsingCallback(object : FirebaseCallback {
//            override fun onResponse(response: Response) {
//              binding.profileUsername.text = response.user.toString()!!
//            }
//        })
//    }


    fun showUserDetails(){

        val getuser = database.getReference(RemoteConstant.usersRef)
        val checkUser = getuser.child("YyN3cSsQGrekXnWB7b4JG6W3vso2")
         checkUser.addListenerForSingleValueEvent(
             object : ValueEventListener {
                 override fun onDataChange(snapshot: DataSnapshot) {
                     if(snapshot.exists()){
                         val user = snapshot.getValue<User>()
                         val userName = user?.userName
                         binding.profileUsername.text = userName.toString()

//                         val imageUrl = snapshot.child("profilePicLink").value.toString()
//                         Glide.with(this@ProfileFragment).load(imageUrl)
//                             .placeholder(R.drawable.ic_face).into(binding.profileImage)



                     }

                 }

                 override fun onCancelled(error: DatabaseError) {
                     TODO("Not yet implemented")
                 }

             }
         )



        }

    private fun setUpTab(tabLayout: TabLayout) {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_vegan_beauty)
                }
                else -> {
                    tab.setIcon(R.drawable.ic_dehydrated)
                }
            }

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


//    fun getUser(user: User){
//    val postListener = object : ValueEventListener {
//        override fun onDataChange(dataSnapshot: DataSnapshot) {
//            // Get Post object and use the values to update the UI
//            val post = dataSnapshot.getValue<User>()
//
//
//            // ...
//        }
//
//        override fun onCancelled(databaseError: DatabaseError) {
//            // Getting Post failed, log a message
//            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//        }
//    }
//    database.getReference(user.uid).addValueEventListener(postListener)
//    }

