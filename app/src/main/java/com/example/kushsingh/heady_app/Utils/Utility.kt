package com.example.kushsingh.heady_app.Utils

import android.content.Context
import android.net.ConnectivityManager
import org.jetbrains.anko.connectivityManager
import org.json.JSONArray
import org.json.JSONException
import java.util.*

class Utility {
    companion object {
        @SuppressWarnings("unchecked")
        fun <T> getListFromJsonArray(jsonArray: JSONArray): List<T> {
            val list = ArrayList<T>()
            for (i in 0 until jsonArray.length()) {
                try {
                    list.add(jsonArray.get(i) as T)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return list

        }

        fun checkNotNull(o: Any?, s: String) {
            if(o == null){
                throw IllegalStateException()
            }
        }

        fun isNetworkConnected(context: Context): Boolean {
            if (context == null)
                return false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }
    }

}