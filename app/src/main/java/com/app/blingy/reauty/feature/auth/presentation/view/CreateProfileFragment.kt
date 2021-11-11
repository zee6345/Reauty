package com.app.blingy.reauty.feature.auth.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.util.connectivity.ConnectionLiveData
import com.app.blingy.reauty.core.util.extension.shortSnackBar
import com.app.blingy.reauty.databinding.FragmentCreateProfileBinding
import com.app.blingy.reauty.databinding.LayoutBottomSheetDatePickerBinding
import com.app.blingy.reauty.databinding.LayoutBottomSheetGenderBinding
import com.app.blingy.reauty.feature.auth.presentation.viewmodel.UpdateViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class CreateProfileFragment : Fragment() {

    companion object {
        const val MIN_AGE = 16
    }

    private var _binding: FragmentCreateProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UpdateViewModel by viewModels()

    @Inject
    lateinit var connectivity: ConnectionLiveData

    private var name: String? = null
    private var birthday: String? = null
    private var gender: String? = null
    private var userAge: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        observeConnectivity()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        birthdayButtonClicked()
        genderButtonClicked()
        finishProfileButtonClicked()
        eulaTextClicked()
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

    private fun birthdayButtonClicked() {
        binding.btnBirthday.setOnClickListener {
            setupBirthdayBottomSheetDialog()
        }
    }

    private fun genderButtonClicked() {
        binding.btnGender.setOnClickListener {
            setupGenderBottomSheetDialog()
        }
    }

    private fun setupGenderBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = LayoutBottomSheetGenderBinding.inflate(layoutInflater)
        bottomSheetDialog.apply {
            setContentView(bottomSheetView.root)
            show()
        }
        bottomSheetView.btnMale.setOnClickListener {
            updateGenderUi(bottomSheetView.btnMale.text.toString())
            dismissBottomSheetDialog(bottomSheetDialog)
        }
        bottomSheetView.btnFemale.setOnClickListener {
            updateGenderUi(bottomSheetView.btnFemale.text.toString())
            dismissBottomSheetDialog(bottomSheetDialog)
        }
        bottomSheetView.btnUndefined.setOnClickListener {
            updateGenderUi(bottomSheetView.btnUndefined.text.toString())
            dismissBottomSheetDialog(bottomSheetDialog)
        }
    }

    private fun updateGenderUi(value: String) {
        gender = value
        binding.btnGender.text = value
    }

    private fun dismissBottomSheetDialog(bsd: BottomSheetDialog) {
        bsd.dismiss()
    }

    private fun setupBirthdayBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetView = LayoutBottomSheetDatePickerBinding.inflate(layoutInflater)
        bottomSheetDialog.apply {
            setContentView(bottomSheetView.root)
            bottomSheetView.dpBirthday.maxDate = Calendar.getInstance().timeInMillis
            show()
        }
        bottomSheetView.btnSelect.setOnClickListener {
            bottomSheetView.dpBirthday.apply {
                birthday = "$dayOfMonth - "
                birthday += "${month + 1} - "
                birthday += "$year"
                userAge = calculateAge(year, month, dayOfMonth)
            }
            // to validate the age of user
            validateAge(userAge ?: 0, birthday.toString())
            bottomSheetDialog.dismiss()
        }
    }

    private fun validateAge(age: Int, birthday: String) {
        if (age < MIN_AGE) {
            binding.tvErrorAge.text = resources.getString(R.string.text_error_birthday)
        } else {
            binding.btnBirthday.text = birthday
        }
    }

    private fun calculateAge(year: Int, month: Int, dayOfMonth: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
            ).years
        } else {
            val currentYear = Calendar.getInstance()
                .get(Calendar.YEAR)
            userAge = currentYear - year
            return userAge as Int
        }
    }

    private fun finishProfileButtonClicked() {
        binding.btnFinishProfile.setOnClickListener {
            val userName = binding.edtUsername.text?.trim().toString()
            if (userName.isNotBlank()) {
                // name is not black
                name = userName
                binding.edtUsername.setText(name)
                validateAge(userAge ?: 0, "")
                val user = User(
                    userName = name.toString(),
                    gender = gender ?: resources.getString(R.string.text_undefined),
                    age = "$userAge years old" ?: ""
                )
                updateUserData(user)
            } else {
                // name is blank, show error
                binding.edtUsername.error = "User name shouldn't be empty"
            }
        }
    }

    private fun navigateToUniqueNameFragment() {
        val action =
            CreateProfileFragmentDirections.actionCreateProfileFragmentToCreateUniqueNameFragment()
        findNavController()
            .navigate(action)
    }

    private fun updateUserData(user: User) {
        lifecycleScope.launch {
            viewModel.updateUserData(user)
            navigateToUniqueNameFragment()
        }
    }

    private fun toggleViewVisibility(value: Boolean) {
        if (value) {
            binding.apply {
                btnBirthday.isEnabled = true
                btnGender.isEnabled = true
                btnFinishProfile.isEnabled = true
            }
        } else {
            binding.apply {
                btnBirthday.isEnabled = false
                btnGender.isEnabled = false
                btnFinishProfile.isEnabled = false
            }
        }
    }

    private fun eulaTextClicked() {
        binding.tvEula.setOnClickListener {
            navigateToEulaFragment()
        }
    }

    private fun navigateToEulaFragment() {
        val action = CreateProfileFragmentDirections.actionCreateProfileFragmentToEulaFragment()
        findNavController().navigate(action)
    }

}