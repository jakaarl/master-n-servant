package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.websocket.WebSocketServerSession
import java.lang.IllegalArgumentException
import java.util.*

class Room(val name: String, val id: UUID) {

    private val converter: MessageConverter = MessageConverter(ObjectMapper())
    private val sessions: MutableMap<String, WebSocketServerSession> = mutableMapOf()

    suspend fun join(nick: String, session: WebSocketServerSession) {
        if (sessions.containsKey(nick)) {
            throw IllegalArgumentException("User ${nick} already in room ${name}.")
        }
        val join = JoinMessage(id, nick)
        sendAll(converter.serialize(join))
        sessions[nick] = session
    }

    suspend fun leave(nick: String) {
        val session = sessions[nick] ?: throw IllegalArgumentException("User ${nick} not in room ${name}.")
        sessions.remove(nick)
        session.close(CloseReason(CloseReason.Codes.NORMAL, "Left ${name}."))
        val leave = LeaveMessage(id, nick)
        sendAll(converter.serialize(leave))
    }

    suspend fun broadcast(sender: String, message: String) {
        val broadcast = BroadcastMessage(id, sender, message)
        sendAll(converter.serialize(broadcast))
    }

    private suspend fun sendAll(message: String) {
        for ((_, session) in sessions) {
            session.outgoing.send(Frame.Text(message))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        return if (other is Room) {
            name == other.name && id == other.id
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return 13 * id.hashCode() + 21 * name.hashCode()
    }
}
