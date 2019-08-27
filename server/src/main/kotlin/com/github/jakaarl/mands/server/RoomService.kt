package com.github.jakaarl.mands.server

import java.lang.IllegalArgumentException
import java.util.*

class RoomService(private val rooms: MutableMap<UUID, Room> = mutableMapOf()) {

    fun listRooms(): List<Pair<UUID, String>> {
        return rooms.entries.map { entry ->
            Pair(entry.key, entry.value.name)
        }
    }

    fun getRoom(id: UUID): Room {
        return rooms[id] ?: throw IllegalArgumentException("No such room: ${id}")
    }

    fun createRoom(name: String): Room {
        val room = Room(name, UUID.randomUUID())
        rooms[room.id] = room
        return room
    }

    fun removeRoom(id: UUID): Room {
        val room = rooms[id] ?: throw IllegalArgumentException("No such room: ${id}")
        rooms.remove(id)
        return room
    }
}
