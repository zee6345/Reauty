package com.app.blingy.reauty.feature.profile.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.blingy.reauty.databinding.FragmentProfileOptionBinding
import com.app.blingy.reauty.feature.profile.adapter.CustomAdapter
import com.app.blingy.reauty.feature.profile.utils.OptionsItemDataSource


class ProfileOptionFragment : Fragment() {
    private var _binding: FragmentProfileOptionBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentProfileOptionBinding.inflate(inflater,container,false)

        binding.btnMyProfileTV.setOnClickListener{
//            requireActivity().supportFragmentManager.popBackStack()
        }


        setUpRecyclerView()
        setUpRecyclerView2()

        // Inflate the layout for this fragment
        return  binding.root
    }


    private fun setUpRecyclerView() = binding.rvProfOptions.apply {
        listAdapter = CustomAdapter{items->

            if (items.Items.contentEquals("Edit Profile") ){
                val action = ProfileOptionFragmentDirections.actionProfileOptionFragmentToEditProfileFragment()
                findNavController().navigate(action)
            }

        }


        val adapter1 = listAdapter
        adapter1.submitList(OptionsItemDataSource.optionsItemList)
        adapter = adapter1

        layoutManager = LinearLayoutManager(requireContext())
    }
    private fun setUpRecyclerView2() = binding.rvProfOptions2.apply {
        listAdapter = CustomAdapter{items->

            if (items.Items.contentEquals("Edit Profile") ){
                val action = ProfileOptionFragmentDirections.actionProfileOptionFragmentToEditProfileFragment()
                findNavController().navigate(action)
            }

        }


        val adapter1 = listAdapter
        adapter1.submitList(OptionsItemDataSource.optionsItemList2)
        adapter = adapter1

        layoutManager = LinearLayoutManager(requireContext())
    }




}