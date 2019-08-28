package com.github.jakaarl.mands.server

import org.junit.Test
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PlayServiceTest {

    @Test
    fun listsPlays() {
        val play = Play(UUID.randomUUID(), "Test play")
        val service = PlayService(mapOf(Pair(play.id, play)))
        val plays = service.listPlays()
        assertEquals(1, plays.size)
        assertTrue(plays.contains(play))
    }

    @Test
    fun createsPlay() {
        val service = PlayService()
        val play = service.createPlay("Test play")
        assertTrue(service.plays.contains(play.id))
    }

    @Test
    fun removesPlay() {
        val play = Play(UUID.randomUUID(),"Test play")
        val service = PlayService(mapOf(Pair(play.id, play)))
        val removedPlay = service.removePlay(play.id)
        assertEquals(play, removedPlay)
        assertFalse(service.plays.contains(play.id))
    }

}
