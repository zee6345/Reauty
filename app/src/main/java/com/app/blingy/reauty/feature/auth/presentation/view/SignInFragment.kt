package com.app.blingy.reauty.feature.auth.presentation.view


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.util.Utils
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import com.app.blingy.reauty.core.util.exhaustive
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.core.util.extension.showKeyboard
import com.app.blingy.reauty.databinding.FragmentSignInBinding
import com.app.blingy.reauty.feature.auth.presentation.contract.AuthContract
import com.app.blingy.reauty.feature.auth.presentation.viewmodel.AuthViewModel
import com.app.blingy.reauty.feature.welcome.presentation.view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


private const val REQUEST_CODE_GOOGLE_SIGN = 8081
private const val KEY_PENDING_EMAIL = "key_pending_email"

@AndroidEntryPoint
class SignInFragment : Fragment() {

    val TAG = SignInFragment::class.simpleName

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var connectivity: ConnectionLiveData
    private lateinit var googleSignInIntent: Intent
    private var emailLink: String = ""
    private var pendingEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Restore the "pending" email address
        if (savedInstanceState != null) {
            pendingEmail = savedInstanceState.getString(KEY_PENDING_EMAIL, null)
            binding.edtEmail.setText(pendingEmail)
        }
    }

    override fun onStart() {
        super.onStart()
        if (activity != null && activity?.intent?.hasExtra(KEY_PENDING_EMAIL) == true) {
            // Check if the Intent that started the Activity contains an email sign-in link.
            checkIntent(activity?.intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView: called")
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observeConnectivity()
        attachOnClickListener()
        initObservers()
    }

    override fun onDestroyView() {
        Timber.d("onDestroyView: called")
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.e(TAG, "onActivityResult: called $requestCode")
        if (resultCode == Activity.RESULT_CANCELED) {
            toggleViewsVisibility(false)
        }
        if (requestCode == REQUEST_CODE_GOOGLE_SIGN) {


            Log.e(TAG, "onActivityResult: inside google auth")

//            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                Log.e(TAG, "onActivityResult: ${account.displayName}")
//            } catch (e: ApiException) {
//                Log.e(TAG, "onActivityResult: ${e.message}")
//            }
//
//            Log.e(TAG, "onActivityResult: ${data!!}")

            viewModel.setEvent(AuthContract.AuthEvent.CredentialSignIn(data!!))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_PENDING_EMAIL, pendingEmail)
    }

    private fun attachOnClickListener() {
        googleSignInButtonClicked()
        emailSignInButtonClicked()
        downArrowImageClicked()
    }

//    private fun observeConnectivity() {
//        connectivity.observe(viewLifecycleOwner, { isConnected ->
//            isConnected?.let {
//                if (it) {
//                    toggleViewsVisibility(true)
//                } else {
//                    toggleViewsVisibility(false)
//                    binding.root.shortSnackBar(
//                        resources.getString(R.string.text_error_internet_connection)
//                    )
//                }
//            }
//        })
//    }

    private fun googleSignInButtonClicked() {
        binding.btnGoogle.setOnClickListener {

            removeEditTextError()
            disableButtons()
            googleSignInIntent = viewModel.getGoogleSignInIntent().signInIntent
            startActivityForResult(googleSignInIntent, REQUEST_CODE_GOOGLE_SIGN)

//            val options = GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.webclient_id))
//                .requestEmail()
//                .build()
//            val googleClient = GoogleSignIn.getClient(requireActivity(), options)
//            val signInIntent: Intent = googleClient.getSignInIntent()
//            startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN)

        }
    }

//    private fun validateEmailAddress() {
//        binding.apply {
//            val email = binding.edtEmail.text?.trim().toString()
//            if (!email.isEmpty()) {
//                Timber.d("validateEmailAddress: email address: $email")
//                if (Utils.isValidEmail(email)) {
//                    toggleViewsVisibility(true)
//                    binding.edtEmail.error = null
//                    pendingEmail = email
//                    viewModel.setEvent(AuthContract.AuthEvent.SendEmailLink(email))
//                } else {
//                    toggleViewsVisibility(false)
//                    requestFocusAndKeyboard()
//                    binding.edtEmail.error = "Please provide valid email address"
//                }
//
//            }
//        }
//    }


    private fun emailSignInButtonClicked() {
        binding.btnLogin.setOnClickListener {
            try {
                val email = binding.edtEmail.text!!.trim().toString()
                if (!email.isEmpty()) {
                    if (Utils.isValidEmail(email)) {

                        toggleViewsVisibility(true)
                        disableButtons()
                        hideKeyboard(binding.edtEmail)
                        pendingEmail = email
                        viewModel.setEvent(AuthContract.AuthEvent.SendEmailLink(email))

                        Log.d(TAG, "emailSignInButtonClicked: $email")

                    } else {
                        toggleViewsVisibility(false)
                        requestFocusAndKeyboard()
                        binding.edtEmail.error = "Please provide valid email address"
                    }
                } else {
                    binding.edtEmail.error = "Please provide email address"
                }
            } catch (e: Exception) {
            }
        }
    }


    private fun initObservers() {
        /// observe the ui state
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it.isLoading) {
                    true -> binding.progress.isVisible = true
                    false -> binding.progress.isVisible = false
                }.exhaustive
                if (it.isSignInFinished) {
                    // navigateToCrateProfile()
                }
            }
        }
        /// observe the error state
        lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect {
                when (it) {
                    is AuthContract.AuthSideEffect.ShowSnackBar -> {
                        binding.progress.isVisible = false
                        showSnackBar(it.message)
                    }
                }.exhaustive
            }
        }
    }

    private fun showSnackBar(value: String) {
        binding.root.shortSnackBar(message = value)
    }

    /**
     * Determine if the given Intent contains an email sign-in link.
     */
    private fun intentHasEmailLink(intent: Intent?): Boolean {
        if (intent != null && intent.data != null) {
            val intentData = intent.data.toString()
            if (viewModel.isSignInWithEmailLink(intentData)) {
                return true
            }
        }
        return false
    }

    /**
     * Check to see if the Intent has an email link, and if so set up the UI accordingly.
     * This can be called from either onCreate or onNewIntent, depending on how the Activity
     * was launched.
     */
    private fun checkIntent(intent: Intent?) {
        if (intentHasEmailLink(intent)) {
            emailLink = intent!!.data!!.toString()
            lifecycleScope.launch {
                viewModel.setEvent(
                    AuthContract.AuthEvent.PasswordLessSignIn(
                        email = pendingEmail,
                        emailLink = emailLink
                    )
                )
                navigateToCrateProfile()
            }
        }
    }


    private fun downArrowImageClicked() {
        binding.imvDownArrow.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun toggleViewsVisibility(value: Boolean) {
        if (value) {
            binding.apply {
                btnLogin.isEnabled = false
                btnGoogle.isEnabled = false
            }
        } else {
            binding.apply {
                btnLogin.isEnabled = true
                btnGoogle.isEnabled = true
            }
        }

    }

    private fun requestFocusAndKeyboard() {
        binding.edtEmail.apply {
            this.requestFocus()
            this.showKeyboard()
        }
    }

    private fun removeEditTextError() {
        if (binding.edtEmail.error != null) {
            binding.edtEmail.error = null
        }
    }

    // private fun hideKeyboard(view: View) {

    fun disableButtons() {
        binding.apply {
            // btnApple.isEnabled = false
            btnLogin.isEnabled = false
            btnGoogle.isEnabled = false
        }
    }

    fun hideKeyboard(view: View) {

        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun navigateToCrateProfile() {
        val action = SignInFragmentDirections.actionSignInFragmentToCreateProfileFragment()
        findNavController().navigate(action)
    }

}