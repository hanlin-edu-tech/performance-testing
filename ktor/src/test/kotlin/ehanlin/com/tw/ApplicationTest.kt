package ehanlin.com.tw

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kotlin.test.*
import io.ktor.server.testing.*
import ehanlin.com.tw.plugins.*

class ApplicationTest {
    @Test
    fun testRoot() {
        withTestApplication({ configureHello() }) {
            handleRequest(HttpMethod.Post, "/hello") {
                addHeader("Content-Type", "application/json")
                addHeader("User-Agent", "Ktor/unit-test")
                setBody("""{"user":"ehanlin","message":"您好"}""")
            }.apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(
                    """
                    {
                      "user" : "ehanlin",
                      "message" : "您好",
                      "ip" : "localhost",
                      "referer" : "",
                      "agent" : "Ktor/unit-test"
                    }
                    """.replace(Regex("\\s"), ""),
                    response.content!!.replace(Regex("\\s"), "")
                )
            }
        }
    }
}