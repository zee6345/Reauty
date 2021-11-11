package com.app.blingy.reauty.core.util

import android.Manifest
import android.content.Context
import pub.devrel.easypermissions.EasyPermissions
import java.security.AccessControlContext

object PermissionHandling {

    fun hasPermission(context: Context)=
        EasyPermissions.hasPermissions(
            context,Manifest.permission.ACCESS_MEDIA_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

}