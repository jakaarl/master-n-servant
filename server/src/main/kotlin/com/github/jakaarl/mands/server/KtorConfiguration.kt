package com.github.jakaarl.mands.server

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.routing.routing
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import java.time.Duration

class KtorConfiguration {

    private val roomService = RoomService()

    fun module(application: Application): Unit {
        with (application) {
            install(CallLogging)
            install(DefaultHeaders)
            install(WebSockets) {
                pingPeriod = Duration.ofSeconds(60)
                timeout = Duration.ofSeconds(10)
                maxFrameSize = 8 * 1024 * 1024
            }
            routing {
                webSocket("/{room}") {
                    val room = call.parameters["room"]
                }
            }
        }
    }
}
