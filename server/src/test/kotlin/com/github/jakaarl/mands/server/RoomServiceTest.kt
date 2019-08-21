package com.github.jakaarl.mands.server

import org.junit.Test
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull

class RoomServiceTest {

    @Test(expected = IllegalArgumentException::class)
    fun getRoomThrowsWhenNoSuchRoom() {
        val service = RoomService()
        service.getRoom(UUID.randomUUID())
    }

    @Test
    fun getsRoom() {
        val id = UUID.randomUUID()
        val existing = Room("Test room", id)
        val service = RoomService(mutableMapOf(Pair(id, existing)))
        val room = service.getRoom(id)
        assertEquals(existing, room)
    }

    @Test
    fun createsRoom() {
        val rooms = mutableMapOf<UUID,Room>()
        val service = RoomService(rooms)
        val created = service.createRoom("Test room")
        assertEquals(created, rooms[created.id])
    }

    @Test(expected = IllegalArgumentException::class)
    fun removeRoomThrowsWhenNoSuchRoom() {
        val service = RoomService()
        service.removeRoom(UUID.randomUUID())
    }

    @Test
    fun removesRoom() {
        val id = UUID.randomUUID()
        val existing = Room("Test room", id)
        val rooms = mutableMapOf(Pair(id, existing))
        val service = RoomService(rooms)
        val removed = service.removeRoom(id)
        assertEquals(existing, removed)
        assertNull(rooms[id])
    }
}
