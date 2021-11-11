package com.app.blingy.reauty.feature.auth.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.databinding.FragmentCreateUniqueNameBinding
import com.app.blingy.reauty.feature.auth.presentation.contract.CreateProfileContract
import com.app.blingy.reauty.feature.auth.presentation.viewmodel.UpdateViewModel
import com.app.blingy.reauty.feature.dashboard.presentation.view.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CreateUniqueNameFragment : Fragment() {

    private var _binding: FragmentCreateUniqueNameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpdateViewModel by viewModels()

    @Inject
    lateinit var connectivity: ConnectionLiveData

    private var searchQuery: String = ""

    @Inject
    lateinit var dataStore: AppPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateUniqueNameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeConnectivity()
        observeUiState(searchQuery)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        letGoButtonClicked()
    }

    private fun observeConnectivity() {
        connectivity.observe(viewLifecycleOwner, { isConnected ->
            isConnected?.let {
                if (it) {
                    toggleViewVisibility(true)
                } else {
                    toggleViewVisibility(false)
                    binding.root.shortSnackBar(
                        resources.getString(R.string.text_error_internet_connection)
                    )
                }
            }
        })
    }

    private fun letGoButtonClicked() {
        if (binding.btnGo.isEnabled) {
            binding.btnGo.setOnClickListener {
                toggleViewVisibility(true)
                val uniqueName = binding.edtUsername.text?.trim().toString()
                if (uniqueName.isNotBlank()) {
                    viewModel.setEvent(
                        CreateProfileContract.CreateProfileEvent.IsUniqueNameAvailable(
                            User(userSearchName = uniqueName)
                        )
                    )
                    searchQuery = uniqueName

                    navigateToDashboard()

                } else {
                    // name is blank, show error
                    showSnackBar(resources.getString(R.string.text_error_name))
                }
            }
        }
    }

    private fun observeUiState(query: String) {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state.isLoading) {
                    true -> binding.progress.isVisible = true
                    false -> binding.progress.isVisible = false
                }
                when (state.hasError) {
                    true -> showSnackBar(state.errorMessage)
                    false -> dismiss()
                }
                when (state.isUniqueNameAvailable) {
                    true -> binding.tvErrorUniqueName.text =
                        resources.getString(R.string.text_error_unique_name)
                    false -> {
                        binding.tvErrorUniqueName.text = ""
                        viewModel.setEvent(
                            CreateProfileContract.CreateProfileEvent.UpdateUniqueName(
                                User(userSearchName = query)
                            )
                        )
                    }
                }
            }
        }
    }

    private fun navigateToDashboard() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
        activity?.finish()
    }

    private fun toggleViewVisibility(value: Boolean) {
        binding.apply {
            btnGo.isEnabled = value
        }
    }

    private fun dismiss() {}

    private fun showSnackBar(value: String) {
        binding.root.shortSnackBar(value)
    }

}