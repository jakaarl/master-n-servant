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
    JsonSubTypes.Type(value = CreatePlayCommand::class, name = "CREATE_PLAY")
)
abstract class Command

data class JoinCommand(val room: UUID, val nick: String) : Command()

data class LeaveCommand(val room: UUID, val nick: String) : Command()

data class BroadcastCommand(val room: UUID, val sender: String, val message: String) : Command()

data class CreatePlayCommand(val name: String): Command()
