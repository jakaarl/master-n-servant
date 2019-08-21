package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.request.receiveParameters
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.sessions.*
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.channels.consumeEach
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.time.Duration
import java.util.*

data class Session(val nick: String)

class KtorApplication {

    private val roomService = RoomService()
    private val converter = MessageConverter(ObjectMapper())

    fun module(application: Application) {
        with (application) {
            install(CallLogging)
            install(DefaultHeaders)
            install(Sessions) {
                cookie<Session>("SESSION") // TODO: encrypt cookie
            }
            install(WebSockets) {
                pingPeriod = Duration.ofSeconds(60)
                timeout = Duration.ofSeconds(10)
                maxFrameSize = 8 * 1024 * 1024
            }
            routing {
                root()
            }
        }
    }

    private fun Routing.root() {
        post("/room") {
            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: throw IllegalArgumentException("Missing 'name' parameter.")
            val room = roomService.createRoom(name)
            call.respondText(text = room.id.toString(), status = HttpStatusCode.Created)
        }
        webSocket("/room/{roomId}") {
            val session = call.sessions.get<Session>() ?: throw IllegalStateException("No session present.")
            val roomId = call.parameters["roomId"]
            val room = roomService.getRoom(UUID.fromString(roomId))
            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        when (val message = converter.deserialize(frame.readText())) {
                            is JoinMessage -> room.join(message.nick, this)
                            is LeaveMessage -> room.leave(message.nick)
                            is BroadcastMessage -> room.broadcast(message.sender, message.message)
                        }
                    }
                }
            } finally {
                room.leave(session.nick)
                call.sessions.clear<Session>()
            }
        }
    }
}
