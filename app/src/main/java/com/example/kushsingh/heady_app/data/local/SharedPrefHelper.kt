package com.example.kushsingh.heady_app.data.local

open class SharedPrefHelper {

    companion object {
        @Volatile
        private var INSTANCE: SharedPrefHelper? = null

        fun getInstance(): SharedPrefHelper {
            if (INSTANCE == null) {
                synchronized(SharedPrefHelper::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = SharedPrefHelper()
                }
            }
            return INSTANCE!!
        }
    }

}

