package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class MessageConverterTest {

    private val converter = MessageConverter(ObjectMapper())

    @Test
    fun convertsJoinMessage(): Unit {
        val message = JoinMessage(UUID.randomUUID(), "tester")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsLeaveMessage(): Unit {
        val message = LeaveMessage(UUID.randomUUID(), "tester")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsBroadcastMessage(): Unit {
        val message = BroadcastMessage(UUID.randomUUID(), "tester", "hi mom!")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }
}
