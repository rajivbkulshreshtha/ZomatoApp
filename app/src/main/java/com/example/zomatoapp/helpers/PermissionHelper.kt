package com.example.zomatoapp.helpers

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleObserver
import com.example.zomatoapp.R


class PermissionHelper(val activity: Activity, private val forced: Boolean = false) :
    LifecycleObserver {


    companion object {
        public const val TAG = "PermissionHelper"
    }

    private val PERMISSIONS =
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    private val PERMISSION_REQUEST_CODE = 200

    var onPermissionGranted: () -> Unit = {}
    var onPermissionRejected: () -> Unit = {}
    var onPermissionResult: () -> Unit = {}


    fun performPermissionCheck(activity: Activity) {
        if (checkPermissionGranted(activity)) {
            if (forced) {

                if (statusCheck(activity)) {
                    onPermissionGranted()
                    onPermissionResult()
                } else {
                    buildAlertMessageNoGps(activity)
                }

            } else {
                onPermissionGranted()
                onPermissionResult()

            }

            Log.d(TAG, "performPermissionCheck:true ");

        } else {

            if (forced) {
                if (checkPermissionRationale(activity)) {
                    requestPermission(activity)
                } else {
                    enablePermissionManuallyDialog(activity)
                }
            } else {
                requestPermission(activity)
            }

            Log.d(TAG, "performPermissionCheck:false ");

        }
    }


    fun checkPermissionGranted(context: Context): Boolean {
        return ((ContextCompat.checkSelfPermission(
            context,
            PERMISSIONS[0]
        ) == PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(
            context,
            PERMISSIONS[1]
        ) == PackageManager.PERMISSION_GRANTED))
    }



    private fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            PERMISSIONS,
            PERMISSION_REQUEST_CODE
        )
    }


    //optimise this
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        whenPermissionGranted: (() -> Unit)? = null,
        whenPermissionRejected: (() -> Unit)? = null
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var locationAccepted = false
        when (requestCode) {
            200 -> {

                locationAccepted =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED
            }
        }

        if (locationAccepted) {

            if (whenPermissionGranted != null) whenPermissionGranted() else {
                onPermissionGranted()
            }

        } else {

            if (whenPermissionRejected != null) whenPermissionRejected() else {
                onPermissionRejected()
            }
        }

        onPermissionResult()
    }

    private fun checkPermissionRationale(activity: Activity): Boolean {

        return (ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSIONS[0])
                && ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSIONS[1]))

    }

    private fun enablePermissionManuallyDialog(activity: Activity) {
        val alertDialogBuilder = AlertDialog.Builder(activity, R.style.MyDialogTheme)
        alertDialogBuilder.setTitle("Change Permissions in Settings")
        alertDialogBuilder
            .setMessage("Press on SETTINGS to Manually enable\n" + "Permissions to use GPS Location functionality") // add custome message
            .setCancelable(true)
            .setPositiveButton(
                "SETTINGS"
            ) { dialog, id ->
                val intent =
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri: Uri = Uri.fromParts("package", activity.packageName, null)
                intent.data = uri
                activity.startActivityForResult(intent, 1000)
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


     fun statusCheck(activity: Activity): Boolean {
        val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        return (manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER))
    }

     fun buildAlertMessageNoGps(activity: Activity) {

        val builder = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.MyDialogTheme));
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { dialog, which ->
                activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                dialog?.dismiss()
            }
            .setNegativeButton(
                "No"
            ) { dialog, which ->
                dialog?.dismiss()
            }


        val alert = builder.create();
        alert.show();
    }

}