package com.github.jakaarl.mands.server

import java.util.UUID

class PlayService(internal var plays: Map<UUID,Play> = mapOf()) {

    fun listPlays(): Collection<Play> = plays.values

    fun createPlay(title: String): Play {
        val play = Play(UUID.randomUUID(), title)
        plays = plays + Pair(play.id, play)
        return play
    }

    fun removePlay(id: UUID): Play {
        val play = plays[id] ?: throw IllegalArgumentException("No such play: ${id}")
        plays = plays - id
        return play
    }
}
