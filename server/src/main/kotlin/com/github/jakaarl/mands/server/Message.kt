package com.github.jakaarl.mands.server

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

enum class MessageType {
    JOIN,
    LEAVE,
    BROADCAST
}

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = JoinMessage::class, name = "JOIN"),
    JsonSubTypes.Type(value = LeaveMessage::class, name = "LEAVE"),
    JsonSubTypes.Type(value = BroadcastMessage::class, name = "BROADCAST")
)
abstract class Message

data class JoinMessage(val room: UUID, val nick: String) : Message()

data class LeaveMessage(val room: UUID, val nick: String) : Message()

data class BroadcastMessage(val room: UUID, val sender: String, val message: String) : Message()
