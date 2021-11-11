package com.app.blingy.reauty.feature.profile.view

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.blingy.reauty.R
import com.app.blingy.reauty.core.domain.model.user.User
import com.app.blingy.reauty.core.util.PermissionHandling
import com.app.blingy.reauty.databinding.FragmentEditProfileBinding
import com.app.blingy.reauty.feature.auth.presentation.viewmodel.UpdateViewModel
import com.app.blingy.reauty.feature.profile.viewmodel.UserProfileViewModel
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedbottompicker.TedBottomPicker
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class EditProfileFragment
    : Fragment() , EasyPermissions.PermissionCallbacks{
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

          }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults, this)
    }

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UserProfileViewModel by viewModels()


    private val updateProfileViewModel: UpdateViewModel by viewModels()

    var data : ByteArray? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
//        requestPermission()


        _binding  = FragmentEditProfileBinding.inflate(inflater ,container, false)

        return binding.root

    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestPermission()

        changeProfilePicture()

        lifecycleScope.launch {

            viewModel.run {

                userDataLiveData.observe(viewLifecycleOwner) { user ->
                    Timber.d("User Called  ${user.userName}")
                    binding.edtNamePf.setText("${user.firstName} ${user.lastName}")
                    binding.edtUsernamePf.setText(user.userName)
                    binding.edtshortBiographyPf.setText(user.bioText)
                    binding.edtNamePf.setText("${user.firstName} ${user.lastName}")
                    Glide.with(this@EditProfileFragment).load(user.profilePicLink).into(binding.profileImgViewEt)
                   ////// Glide.with(this@EditProfileFragment).load(user.profileImageUrl).placeholder(
                    Glide.with(this@EditProfileFragment).load(user.profilePicLink).placeholder(
                        R.drawable.ic_default_profile_picture
                    ).into(
                        binding.profileImgViewEt
                    )
                    binding.edtNamePf.setText("${user.firstName} ${user.lastName}")
                    Glide.with(this@EditProfileFragment).load(user.profilePicLink).into(binding.profileImgViewEt)




//                    Glide.with(this@EditProfileFragment).load(user.profileImageUrl)
//                        .placeholder(R.drawable.ic_four_star_face).into(binding.profileImgViewEt)

                }

            }


//            binding.btnMyProfileTV.setOnClickListener{
//                val action = EditProfileFragmentDirections.actionEditProfileFragmentToProfileOptionFragment()
//                findNavController().navigate(action)
//            }


        }

    }


    private fun requestPermission(){
        if (PermissionHandling.hasPermission(requireContext())){
            return
        }else{
            EasyPermissions.requestPermissions(
                this,
                "You need to accept permission to use",
                0,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun changeProfilePicture() {

        binding.btnChangePfPicture.setOnClickListener {
            requestPermission()

            TedBottomPicker.with(requireActivity())

                .show {
                    binding.cropImageView.visibility = View.VISIBLE
                    binding.btnChangePfPicture.visibility = View.INVISIBLE
                    binding.cropImageView.setImageUriAsync(it)
                    binding.cropImageView.cropShape = CropImageView.CropShape.OVAL
//                   val cropedImageUri  =binding.cropImageView.imageUri
//
                }

            binding.btnCropDone.setOnClickListener {
                val cropedImage =  binding.cropImageView.croppedImage
                binding.cropImageView.visibility = View.GONE
                Toast.makeText(requireContext(), "You Chosed ",Toast.LENGTH_SHORT).show()
                Glide.with(this@EditProfileFragment).load(cropedImage)
                    .into(binding.profileImgViewEt)
                binding.btnChangePfPicture.visibility = View.VISIBLE
                val boas = ByteArrayOutputStream()
                cropedImage?.compress(Bitmap.CompressFormat.JPEG,100, boas)
                data = boas.toByteArray()

                viewModel.uploadUserProfilePicture(data!!)
            }

            binding.btnSaveTv.setOnClickListener {
                showProgressFailureAndNavigate()

                updateUserDataProgressNavigate()

            }

        }
    }

    private fun showProgressFailureAndNavigate() {
        viewModel.loadingState.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.progressBar2.visibility = View.VISIBLE
            } else {
                binding.progressBar2.visibility = View.INVISIBLE
            }
        }
        viewModel.showErrorSnackBar.observe(viewLifecycleOwner) {
            if (it == true) {
                Snackbar.make(requireView(), "Opps! Something went wrong", Snackbar.LENGTH_SHORT)
                    .show()

            }else{
                findNavController().popBackStack()
            }
        }
         }

    private fun updateUserData(user: User) {
        lifecycleScope.launch {
            updateProfileViewModel.updateUserData(user)
        }
    }



    private fun updateUserDataProgressNavigate() {

        val userName = binding.edtUsernamePf.text?.trim().toString()
        val bioText = binding.edtshortBiographyPf.text?.trim().toString()
        val userSearchName = binding.edtNamePf.text?.trim().toString()
        val profilePicUri = viewModel.downloadUri.toString()


        updateUserData(User(userName = userName,
            bioText = bioText,
            userSearchName = userSearchName,
            profilePicLink = profilePicUri))

        Timber.d("anothr Url ${profilePicUri}")
        binding.edtUsernamePf.setText(userName.toString())
        showProgressFailureAndNavigate()


    }


    }

