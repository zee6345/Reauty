package com.app.blingy.reauty.feature.search.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.blingy.reauty.databinding.FragmentInfluencersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfluencersFragment : Fragment() {

    private var _binding: FragmentInfluencersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInfluencersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}