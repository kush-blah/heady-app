package com.example.kushsingh.heady_app.base

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.kushsingh.heady_app.R
import com.example.kushsingh.heady_app.Utils.Utility
import org.jetbrains.anko.alert

abstract class BaseActivity : AppCompatActivity() {

    private var permissionsResultCallback: ActivityCompat.OnRequestPermissionsResultCallback? = null


    protected abstract val progressView: View?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgressBar() {
        Utility.checkNotNull(progressView, "Progress bar not initialized")
        progressView?.visibility = View.VISIBLE
    }

    fun dismissProgressBar() {
        Utility.checkNotNull(progressView, "Progress bar not initialized")
        //  if (getProgressView().isShown()) {
        progressView?.visibility = View.GONE
        //   }
    }

    protected fun checkIfNetworkAvailable(): Boolean {
        if (Utility.isNetworkConnected(this@BaseActivity)) {
            return true
        } else {
            Toast.makeText(this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    interface DialogueButton{
        fun positiveText() : String
        fun getPositive()
        fun getNegativeText() :String
        fun getNegative()
    }
}