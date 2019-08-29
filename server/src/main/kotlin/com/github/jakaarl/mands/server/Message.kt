package com.github.jakaarl.mands.server

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "command"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = CreatePlayCommand::class, name = "CREATE_PLAY"),
    JsonSubTypes.Type(value = UpdatePlayCommand::class, name = "UPDATE_PLAY")
)
abstract class Command

data class CreatePlayCommand(val name: String): Command()

data class UpdatePlayCommand(val play: Play): Command()
