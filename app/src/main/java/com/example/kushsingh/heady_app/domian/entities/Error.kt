package com.example.kushsingh.heady_app.domian.entities

import com.squareup.moshi.Json

class Error {

    @Json(name = "statusCode")
    internal var statusCode: Int? = null
    @Json(name = "name")
    internal var name: String? = null
    @Json(name = "message")
    internal var message: String? = null
    @Json(name = "code")
    internal var code: String? = null
    @Json(name = "details")
    internal var details = Details()
    @Json(name = "stack")
    @Transient
    var stack: String? = null
}
