package com.app.blingy.reauty.feature.welcome.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.data.remote.constant.RemoteConstant
import com.app.blingy.reauty.core.domain.model.feed.Feed
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.databinding.FragmentWelcomeBinding
import com.app.blingy.reauty.feature.auth.presentation.view.AuthActivity
import com.app.blingy.reauty.feature.welcome.presentation.adapter.OnBoardingAdapter
import com.app.blingy.reauty.feature.welcome.presentation.contract.WelcomeContract
import com.app.blingy.reauty.feature.welcome.presentation.viewmodel.WelcomeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    @Inject
    lateinit var connectivity: ConnectionLiveData

    @Inject
    lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView: called")
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: called")
        setupUi()
        sentGetOnBoardingDataEvent()
        observeUiState()
        observeConnectivity()
        binding.apply {
            vpContainer.adapter = onBoardingAdapter
            // connect the indicator with viewpager
            indicator.setViewPager(binding.vpContainer)
        }

    }

    override fun onDestroyView() {
        Timber.d("onDestroyView: called")
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        initAdapter()
        buttonClicked()
    }

    private fun initAdapter() {
        Timber.d("initAdapter: called")
        onBoardingAdapter = OnBoardingAdapter()
        Timber.d("initAdapter: adapter item: ${onBoardingAdapter.itemCount}")
    }

    private fun buttonClicked() {
        Timber.d("buttonClicked: ${binding.btnJoinNow.text} called")
        binding.btnJoinNow.setOnClickListener {
            navigateToAuthActivity()

        }
        binding.btnSignIn.setOnClickListener {
            Timber.d("buttonClicked: ${binding.btnSignIn.text} called")
             navigateToAuthActivity()
        }
    }



    private fun sentGetOnBoardingDataEvent() {
        Timber.d("sentGetOnBoardingDataEvent, called")
        lifecycleScope.launch { viewModel.setEvent(WelcomeContract.WelcomeEvent.GetOnBoardingData) }
    }

    private fun observeUiState() {
        Timber.d("observeUiState, called")
        lifecycleScope.launch {
            Timber.d("observeUiState, inside lifecycleScope: called")
            viewModel.uiState.collect { state ->
                if (state.onBoardingPageList.isNotEmpty()) {
                    Timber.d("observeUiState, onBoardingDataSet: ${state.onBoardingPageList}")
                    onBoardingAdapter.submitList(state.onBoardingPageList)
                }
            }
        }
    }

    private fun observeConnectivity() {
        Timber.d("observeConnectivity, called")
        connectivity.observe(viewLifecycleOwner, { isConnected ->
            isConnected?.let {
                Timber.d("observeConnectivity, network connected: $it")
                if (it) {
                    toggleViewVisibility(true)
                } else {
                    toggleViewVisibility(false)
                    // show no internet connection
                    binding.root.shortSnackBar(resources.getString(R.string.text_error_internet_connection))
                }
            }
        })
    }

    private fun toggleViewVisibility(value: Boolean) {
        Timber.d("toggleViewVisibility, called")
        if (value) {
            binding.apply {
                Timber.d("toggleViewVisibility, isEnabled true called")
                btnJoinNow.isEnabled = true
                btnSignIn.isEnabled = true
            }
        } else {
            binding.apply {
                Timber.d("toggleViewVisibility, isEnabled false called")
                btnJoinNow.isEnabled = false
                btnSignIn.isEnabled = false
            }
        }
    }

    private fun navigateToAuthActivity() {
        Timber.d("navigateToAuthActivity: called")
        startActivity(Intent(requireContext(), AuthActivity::class.java))
        activity?.overridePendingTransition(
            R.anim.anim_from_bottom_to_top,
            R.anim.anim_from_top_to_bottom
        )
        activity?.finish()
    }

}