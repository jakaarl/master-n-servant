package com.github.jakaarl.mands.server

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "command"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = JoinCommand::class, name = "JOIN"),
    JsonSubTypes.Type(value = LeaveCommand::class, name = "LEAVE"),
    JsonSubTypes.Type(value = BroadcastCommand::class, name = "BROADCAST"),
    JsonSubTypes.Type(value = CreateRoomCommand::class, name = "CREATE_ROOM")
)
abstract class Command

abstract class Response

data class JoinCommand(val room: UUID, val nick: String) : Command()

data class LeaveCommand(val room: UUID, val nick: String) : Command()

data class BroadcastCommand(val room: UUID, val sender: String, val message: String) : Command()

data class CreateRoomCommand(val name: String): Command()

data class RoomListResponse(val rooms: List<Pair<UUID, String>>) : Response()

data class RoomCreatedResponse(val room: Room) : Response()
