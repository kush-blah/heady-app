package com.example.kushsingh.heady_app.domian.entities

import com.squareup.moshi.Json

public class Details {

    @Json(name = "messages")
    internal var messages = Messages()

    fun getMessages(): Messages {
        return messages
    }

    fun setMessages(messages: Messages) {
        this.messages = messages
    }
}

