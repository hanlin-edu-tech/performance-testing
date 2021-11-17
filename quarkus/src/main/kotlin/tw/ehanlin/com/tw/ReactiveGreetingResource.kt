package tw.ehanlin.com.tw

import io.smallrye.mutiny.Uni
import io.vertx.core.http.HttpServerRequest
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

@Path("/hello")
class ReactiveGreetingResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun hello(
        requestBody: RequestBody,
        @Context httpRequest: HttpServerRequest
    ): Uni<Map<String, String>> {
        val ip = findIpAddr(httpRequest)
        val referer = findReferer(httpRequest)
        val agent = findUserAgent(httpRequest)
        return Uni.createFrom().item(
            mapOf(
                "user" to requestBody.user,
                "message" to requestBody.message,
                "ip" to ip,
                "referer" to referer,
                "agent" to agent
            )
        )
    }

    fun findIpAddr(httpRequest: HttpServerRequest): String =
        httpRequest.headers().get("x-forwarded-for") ?: httpRequest.remoteAddress().hostAddress()

    fun findReferer(httpRequest: HttpServerRequest): String =
        httpRequest.headers().get("referer") ?: ""

    fun findUserAgent(httpRequest: HttpServerRequest): String =
        httpRequest.headers().get("User-Agent") ?: ""

    data class RequestBody(
        val user: String,
        val message: String
    )
}