package ehanlin.com.tw

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ehanlin.com.tw.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureHello()
    }.start(wait = true)
}
