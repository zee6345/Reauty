package com.app.blingy.reauty.feature.auth.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.data.preference.AppPreferences
import com.app.blingy.reauty.databinding.ActivityAuthBinding
import com.app.blingy.reauty.feature.auth.presentation.viewmodel.AuthViewModel
import com.app.blingy.reauty.feature.dashboard.presentation.view.DashboardActivity
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {


    val TAG = AuthActivity::class.simpleName

    private lateinit var binding: ActivityAuthBinding

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var dataStore: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        // to show the app theme
        setTheme(R.style.Reauty_Light)
        super.onCreate(savedInstanceState)
        //setupLaunchFlow()
        Timber.d("onCreate: called")
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDynamicLink()
        setupNavController()

    }

    override fun onStart() {
        super.onStart()
        getDynamicLink()
    }


    //activity result for fragment
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK) {
//            try {
//                SignInFragment().onActivityResult(requestCode, resultCode, data)
//            } catch (e: Exception) {
//            }
//        }
//
//    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     * to get the email link that return from user email app or browser
     */
    private fun getDynamicLink() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnSuccessListener {
                handleSuccess()
            }
            .addOnFailureListener {
                Timber.i("getDynamicLink: failure: ${it.localizedMessage}")
            }
    }

    /**
     * to sign in the user with email link and email address
     */
    private fun handleSuccess() {
        val intent = intent
        Timber.d("handleSuccess: intent: $intent")
        val emailLink = intent.data.toString()
        if (emailLink != null) {
            Timber.d("handleSuccess: email link: ${intent.data.toString()}")
            if (viewModel.isSignInWithEmailLink(emailLink)) {
                viewModel.getEmailAddress()
                val emailAddress = viewModel.uiState.value.emailAddress
                if (!emailAddress.isNullOrEmpty()) {
                    Timber.d("handleSuccess: email address: $emailAddress")
                    viewModel.signInWithEmailLink(email = emailAddress, emailLink = emailLink)
                    Timber.d("handleSuccess: inside uiState called")
                    Timber.d("handleSuccess: isSignInFinished: ${viewModel.uiState.value.isSignInFinished}")
                    navigateToCreateProfile()
                } else {
                    Toast.makeText(this, "Please use valid link!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupNavController() {
        Timber.d("setupNavController: called")
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    private fun setupLaunchFlow() {
        lifecycleScope.launch {
            dataStore.hasCreateProfile().collect { hasProfile ->
                if (!hasProfile) {
                    // user already have profile
                    dataStore.hasUniqueName().collect { hasUniqueName ->
                        if (!hasUniqueName) {
                            // user have unique name
                            navigateToDashboard()
                        } else {
                            // to create unique name (search name)
                            navigateToCreateUniqueName()
                        }
                    }
                } else {
                    // to create profile for user
                    navigateToCreateProfile()
                }
            }
        }
    }

    private fun navigateToCreateProfile() {
        Timber.d("navigateToCreateProfile: called()")
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.createProfileFragment)
    }

    private fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    private fun navigateToCreateUniqueName() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController.navigate(R.id.createUniqueNameFragment)
    }

}