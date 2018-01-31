package com.example.kushsingh.heady_app.domian.entities

import com.squareup.moshi.Json

class Messages {

    @Json(name = "error")
    var error: List<String>? = null

    var errorMessages: Map<String, List<String>>? = null
}