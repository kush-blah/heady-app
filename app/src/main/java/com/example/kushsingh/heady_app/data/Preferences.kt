package com.example.kushsingh.heady_app.data

import com.example.kushsingh.heady_app.HeadyApplication
import com.example.kushsingh.heady_app.SharedPreferenceManager

object Preferences {

    val APP = "app"

    private lateinit var sAppInstance: SharedPreferenceManager


    fun app(): SharedPreferenceManager {
        if (sAppInstance == null) {
            sAppInstance = SharedPreferenceManager(HeadyApplication.contextProvider(), APP)
        }
        return sAppInstance
    }


}
