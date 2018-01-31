package com.example.kushsingh.heady_app

import com.example.kushsingh.heady_app.base.ErrorWrapper

class ErrorImpl(private val statusCode: Int, private val message: String, private val statusName: String, private val code: String?, private val errorMsgs: Map<String, List<String>>?) : ErrorWrapper {

    override val errorMessages: Map<String, List<String>>
        get() = errorMessages

    override val errorCode: String
        get() = errorCode

    override val errorMessage: String
        get() = message

    override val errorStatusCode: Int
        get() = statusCode

    override val errorStatus: String
        get() = statusName
}