package com.github.jakaarl.mands.server

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.cio.websocket.Frame
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import kotlinx.coroutines.channels.consumeEach
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

class KtorApplication {
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(KtorApplication::class.java)
    }

    private val converter = MessageConverter(ObjectMapper())
    private val playService = PlayService()

    fun module(application: Application) {
        with (application) {
            install(CallLogging)
            install(DefaultHeaders)
            install(ContentNegotiation) {
                jackson {  }
            }
            install(CORS) {
                allowCredentials = true
                allowSameOrigin = true
                header(HttpHeaders.Accept)
                header(HttpHeaders.AccessControlAllowOrigin)
                header(HttpHeaders.AccessControlAllowCredentials)
                header(HttpHeaders.ContentType)
                header(HttpHeaders.Origin)
                maxAge = Duration.ofHours(24)
                method(HttpMethod.Get)
                method(HttpMethod.Post)
                method(HttpMethod.Patch)
                // TODO: proper CORS host configuration
                host(host = "localhost:4200", schemes = listOf("http", "ws"))
            }
            //install(Sessions) {
                //cookie<Session>("SESSION") // TODO: encrypt cookie
            //}
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
        get("/plays") {
            LOGGER.debug("Listing plays.")
            val plays = playService.listPlays()
            call.respond(plays)
        }
        post("/plays") {
            val createPlay = call.receive<CreatePlayCommand>()
            LOGGER.debug("Creating play ${createPlay.name}.")
            val play = playService.createPlay(createPlay.name)
            call.respond(HttpStatusCode.Created, play)
        }
        patch("/plays") {
            val updatePlay = call.receive<UpdatePlayCommand>()
            LOGGER.debug("Updating play ${updatePlay.play.id}.")
            val updated = playService.updatePlay(updatePlay.play)
            call.respond(HttpStatusCode.OK, updated)
        }

        webSocket("/ws") {
            LOGGER.debug("Web socket connected.")
            try {
                incoming.consumeEach { frame ->
                    if (frame is Frame.Text) {
                        /*when (val message = converter.deserialize(frame.readText())) {
                            is JoinCommand -> roomService.getRoom(message.room).join(message.nick, this)
                            is LeaveCommand -> roomService.getRoom(message.room).leave(message.nick)
                            is BroadcastCommand -> roomService.getRoom(message.room).broadcast(message.sender, message.message)
                        } */ // TODO: default case -> error
                    }
                }
            } finally {
                //call.sessions.clear<Session>()
            }
        }
    }
}
