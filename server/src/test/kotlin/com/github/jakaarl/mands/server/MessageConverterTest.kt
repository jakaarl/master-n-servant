package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class MessageConverterTest {

    private val converter = MessageConverter(ObjectMapper())

    @Test
    fun convertsCreatePlayCommand() {
        val message = CreatePlayCommand("Test play")
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    @Test
    fun convertsUpdatePlayCommand() {
        val edited = Play(UUID.randomUUID(), "Test play")
        val message = UpdatePlayCommand(edited)
        val serialized = converter.serialize(message)
        val deserialized = converter.deserialize(serialized)
        assertEquals(message, deserialized)
    }

    // TODO parameterized tests
}
