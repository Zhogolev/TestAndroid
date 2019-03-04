package com.example.myvoiceapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast

class CameraActivity : Activity() {
    private val mSavedInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        val intent = intent

        when {
            needPermissions(this) -> requestPermissions()
            intent != null -> {
                intent.component = null
                intent.setPackage("com.google.android.GoogleCamera")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            else -> finish()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.d(TAG, "onRequestPermissionsResult: ")
        when (requestCode) {
            PERMISSIONS_REQUEST_ALL_PERMISSIONS -> {
                var hasAllPermissions = true
                for (i in grantResults.indices) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        hasAllPermissions = false
                        Log.e(TAG, "Unable to get permission " + permissions[i])
                    }
                }
                if (hasAllPermissions) {
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Unable to get all required permissions", Toast.LENGTH_LONG
                    ).show()
                    finish()
                    return
                }
            }
            else -> Log.e(TAG, "Unexpected request code")
        }
    }

    private fun requestPermissions() {
        Log.d(TAG, "requestPermissions: ")
        val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        requestPermissions(permissions, PERMISSIONS_REQUEST_ALL_PERMISSIONS)
    }

    companion object {

        private const val TAG = "CameraActivity"
        private const val PERMISSIONS_REQUEST_ALL_PERMISSIONS = 1

        fun needPermissions(activity: Activity): Boolean {
            Log.d(TAG, "needPermissions: ")
            Log.d(TAG, "Camera permission : " + activity.checkSelfPermission(Manifest.permission.CAMERA))
            Log.d(TAG, "Camera permission : " + activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))

            return activity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        }
    }
}