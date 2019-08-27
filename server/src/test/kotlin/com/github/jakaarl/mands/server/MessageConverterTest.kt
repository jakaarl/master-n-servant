package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class MessageConverterTest {

    private val converter = MessageConverter(ObjectMapper())

    @Test
    fun convertsJoinCommand() {
        val message = JoinCommand(UUID.randomUUID(), "tester")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsLeaveCommand() {
        val message = LeaveCommand(UUID.randomUUID(), "tester")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsBroadcastCommand() {
        val message = BroadcastCommand(UUID.randomUUID(), "tester", "hi mom!")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsCreateRoomCommand() {
        val message = CreateRoomCommand("Test room")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    // TODO parameterized tests
}
