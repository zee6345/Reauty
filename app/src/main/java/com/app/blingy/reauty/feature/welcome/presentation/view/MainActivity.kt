package com.app.blingy.reauty.feature.welcome.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import com.app.blingy.reauty.databinding.ActivityMainBinding
import com.app.blingy.reauty.feature.auth.presentation.view.AuthActivity
import com.app.blingy.reauty.feature.dashboard.presentation.view.DashboardActivity
import com.app.blingy.reauty.feature.welcome.presentation.contract.WelcomeContract
import com.app.blingy.reauty.feature.welcome.presentation.viewmodel.WelcomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: WelcomeViewModel by viewModels()

    @Inject
    lateinit var connectivity: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        // to show the app theme
        setTheme(R.style.Reauty_Light)
        super.onCreate(savedInstanceState)
        setupLaunchFlow()
        Timber.d("onCreate: called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController()
    }

    private fun setupNavController() {
        Timber.d("setupNavController: called")
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    /**
     * whether the user is already login nor not
     * if already login send user to [DashboardActivity]
     */
    private fun setupLaunchFlow() {
        Timber.d("setupLaunchFlow: called")
        observeConnectivity()
    }

    private fun observeConnectivity() {
        Timber.d("observeConnectivity, called")
        connectivity.observe(this, { isConnected ->
            isConnected?.let {
                Timber.d("observeConnectivity, network connected: $it")
                if (it) {
                    sentHasSignInEvent()
                    lifecycleScope.launch {
                        viewModel.uiState.collect { state ->
                            when (state.hasSignIn) {
                                true -> navigateToDashboardActivity()
                                false -> idle()
                            }
                        }
                    }
                    //sentHasUniqueNameEvent()
                    //observeUiState()
                }
            }
        })
    }

    private fun sentHasSignInEvent() {
        Timber.d("sentHasSignInEvent, called")
        lifecycleScope.launch {
            viewModel.setEvent(WelcomeContract.WelcomeEvent.HasSignIn)
        }
    }

    private fun sentHasProfileEvent() {
        Timber.d("sentHasProfileEvent, called")
        lifecycleScope.launch {
            viewModel.setEvent(WelcomeContract.WelcomeEvent.HasProfile)
        }
    }

    private fun sentHasUniqueNameEvent() {
        Timber.d("sentHasUniqueNameEvent, called")
        lifecycleScope.launch {
            viewModel.setEvent(WelcomeContract.WelcomeEvent.HasUniqueName)
        }
    }

    private fun observeUiState() {
        Timber.d("observeUiState, called")
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                Timber.d("observeUiState, hasUniqueName: ${state.hasUniqueName}")
                when (state.hasUniqueName) {
                    true -> navigateToDashboardActivity()
                    false -> {
                        sentHasProfileEvent()
                        Timber.d("observeUiState, hasProfile: ${state.hasProfile}")
                        when (state.hasProfile) {
                            true -> navigateToAuthActivity()
                            false -> {
                                sentHasSignInEvent()
                                Timber.d("observeUiState, hasSignIn: ${state.hasSignIn}")
                                when (state.hasSignIn) {
                                    true -> navigateToAuthActivity()
                                    false -> idle()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun idle() {
        Timber.d("idle, called")
    }

    private fun navigateToDashboardActivity() {
        Timber.d("navigateToDashboardActivity, called")
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    private fun navigateToAuthActivity() {
        Timber.d("navigateToAuthActivity, called")
        startActivity(Intent(this, AuthActivity::class.java))
    }

}
