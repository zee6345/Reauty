package com.app.blingy.reauty.feature.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.blingy.reauty.databinding.FragmentUploadPictureBinding
import gun0912.tedbottompicker.TedBottomPicker
import android.widget.Toast
import com.app.blingy.reauty.core.util.PermissionHandling.hasPermission

import com.app.blingy.reauty.feature.welcome.presentation.view.MainActivity
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.jar.Manifest


class UploadPictureFragment : Fragment(),EasyPermissions.PermissionCallbacks {
    private var _binding: FragmentUploadPictureBinding? = null
    private val binding get() = _binding!!
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
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
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {



        _binding = FragmentUploadPictureBinding.inflate(inflater,container,false)






        // Inflate the layout for this fragment
        return binding.root
    }

    private fun requestPermission(){
        if (hasPermission(requireContext())){
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




}