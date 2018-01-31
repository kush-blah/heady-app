package com.example.kushsingh.heady_app.Utils

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
    }

}