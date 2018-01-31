package com.example.kushsingh.heady_app.helper

import com.example.kushsingh.heady_app.ErrorImpl
import com.example.kushsingh.heady_app.Utils.ErrorConstants
import com.example.kushsingh.heady_app.Utils.Utility
import com.example.kushsingh.heady_app.base.ErrorWrapper
import com.example.kushsingh.heady_app.domian.entities.Error
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

class ErrorWrapperHelper @Inject
internal constructor(private val moshi: Moshi) {
    var status = ""
    private val codes = arrayListOf(400, 401, 422)

    fun transformToErrorWrapper(error: String?): ErrorWrapper {
        val errorObj = Error()
        try {
            val errorJson = JSONObject(error)
            val errorJsonObj = errorJson.getJSONObject("error")
            errorObj.statusCode = errorJsonObj.getInt("statusCode")
            if (codes.find { it == errorObj.statusCode } != null) {
                errorObj.code = errorJsonObj.optString("code")
                errorObj.message = errorJsonObj.getString("message")
                errorObj.name = errorJsonObj.getString("name")
                if (errorJsonObj.optJSONObject("details") != null) {
                    val detailsMsgsJson = errorJsonObj.optJSONObject("details").optJSONObject("messages")
                    if (detailsMsgsJson != null) {
                        val detailsMap = HashMap<String, List<String>>()
                        val it = detailsMsgsJson.keys()
                        var currentIndex = 0
                        val type = Types.newParameterizedType(List::class.java, JSONArray::class.java)
                        val adapter = moshi.adapter<List<String>>(type)
                        it.forEach {
                            Timber.d("current details msg" + it)
                            Timber.d("json array" + detailsMsgsJson.getJSONArray(it))
                            detailsMap[it] = Utility.getListFromJsonArray(detailsMsgsJson.getJSONArray(it))
                            currentIndex++
                        }
                        errorObj.details.messages.errorMessages = detailsMap
                    }
                }
            } else {
                errorObj.code = ErrorConstants.ERROR_CODE
                errorObj.message = ErrorConstants.ERROR_MESSAGE
            }
        } catch (e: JSONException) {
            Timber.d(e)
        }
        val detailsMsgs = errorObj.details.messages.errorMessages
        return ErrorImpl(errorObj.statusCode ?: 900, errorObj.message
                ?: "Blank Error", errorObj.name ?: "Empty Error Node", errorObj.code, detailsMsgs)
    }

    fun transformToNetworkWrapper(e: Exception): ErrorWrapper {
        val statusCode = ErrorConstants.NETWORK_ERROR_STATUS_CODE
        val status = ErrorConstants.NETWORK_ERROR_STATUS
        val message = ErrorConstants.ERROR_MESSAGE
        val code = ErrorConstants.ERROR_CODE
        return ErrorImpl(statusCode, message, status, code, null)
    }

    fun transformToUnexpected(t: Throwable): ErrorWrapper {
        val statusCode: Int = if (t is InterruptedException)
            ErrorConstants.NETWORK_CANCEL_REQ_CODE
        else
            ErrorConstants.NETWORK_ERROR_STATUS_CODE

        val status = ErrorConstants.NETWORK_ERROR_STATUS
        val message = ErrorConstants.ERROR_MESSAGE
        val code = ErrorConstants.ERROR_CODE
        return ErrorImpl(statusCode, message, status, code, null)
    }

}
