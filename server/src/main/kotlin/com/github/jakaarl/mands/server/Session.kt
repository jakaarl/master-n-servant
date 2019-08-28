package com.github.jakaarl.mands.server

import java.util.UUID

enum class SessionType {
    MASTER,
    SPECTATOR
}

data class Session(val id: UUID, val type: SessionType)
