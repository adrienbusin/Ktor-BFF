package com.decathlon

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.decathlon.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
