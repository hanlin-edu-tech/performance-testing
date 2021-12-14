package ehanlin.com.tw.plugins

import io.ktor.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*

fun Application.configureHello() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        post("/hello") {
            val requestBody = call.receive<RequestBody>()
            val ip = findIpAddr(call.request)
            val referer = findReferer(call.request)
            val agent = findUserAgent(call.request)

            call.respond(
                mapOf(
                    "user" to requestBody.user,
                    "message" to requestBody.message,
                    "ip" to ip,
                    "referer" to referer,
                    "agent" to agent
                )
            )
        }
    }
}

fun findIpAddr(httpRequest: ApplicationRequest): String =
    httpRequest.headers.get("x-forwarded-for") ?: httpRequest.origin.remoteHost

fun findReferer(httpRequest: ApplicationRequest): String =
    httpRequest.headers.get("referer") ?: ""

fun findUserAgent(httpRequest: ApplicationRequest): String =
    httpRequest.headers.get("User-Agent") ?: ""


data class RequestBody(
    val user: String,
    val message: String
)
