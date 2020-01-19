package com.example.zomatoapp.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.zomatoapp.R
import com.example.zomatoapp.helpers.PermissionHelper
import com.example.zomatoapp.view.base.BaseActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper


class SplashActivity : BaseActivity() {

    var permissionHelper = PermissionHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initPermission()

    }

    private fun initPermission() {
        permissionHelper.onPermissionResult = { doOnPermissionResult() }
        permissionHelper.performPermissionCheck(this)
    }


    private fun doOnPermissionResult() {
        val handler = Handler()
        handler.postDelayed(Runnable {
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 1500)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionHelper.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

}
