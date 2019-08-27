package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class MessageConverter(val objectMapper: ObjectMapper) {

    init {
        objectMapper.registerModule(KotlinModule())
    }

    fun serialize(message: Any): String {
        return objectMapper.writeValueAsString(message)
    }

    fun deserialize(message: String): Command {
        return objectMapper.readValue(message, Command::class.java)
    }
}
