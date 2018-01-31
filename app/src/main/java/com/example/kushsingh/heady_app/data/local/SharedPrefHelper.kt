package com.example.kushsingh.heady_app.data.local

open class SharedPrefHelper {
    @Volatile
    private lateinit var INSTANCE: SharedPrefHelper

    companion object {
        @Synchronized
        fun getInstance(): SharedPrefHelper {
            return SharedPrefHelper()
        }
    }
}

