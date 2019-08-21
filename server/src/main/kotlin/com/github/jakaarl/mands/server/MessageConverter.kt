package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class MessageConverter(val objectMapper: ObjectMapper) {

    init {
        objectMapper.registerModule(KotlinModule())
    }

    fun serialize(message: Message): String {
        return objectMapper.writeValueAsString(message);
    }

    fun deserialize(message: String): Message {
        return objectMapper.readValue(message, Message::class.java)
    }
}
