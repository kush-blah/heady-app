package com.example.kushsingh.heady_app.base

interface ErrorWrapper {

    val errorStatusCode: Int

    val errorMessage: String

    val errorMessages: Map<String, List<String>>

    val errorStatus: String

    val errorCode: String
}
