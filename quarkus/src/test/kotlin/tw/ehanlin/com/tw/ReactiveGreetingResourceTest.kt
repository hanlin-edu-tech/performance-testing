package tw.ehanlin.com.tw

import com.fasterxml.jackson.databind.ObjectMapper
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ReactiveGreetingResourceTest {

    val objectMapper: ObjectMapper = ObjectMapper()

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`()
            .contentType("application/json; charset=UTF-8")
            .body(objectMapper.writeValueAsString(buildRequest()))
            .post("/hello")
            .then()
            .statusCode(200)
            .body(`is`("{\"user\":\"ehanlin\",\"message\":\"您好\",\"ip\":\"127.0.0.1\",\"referer\":\"\",\"agent\":\"Apache-HttpClient/4.5.13 (Java/17.0.1)\"}"))
    }

    fun buildRequest(): Map<String, Any> = mapOf(
        "user" to "ehanlin",
        "message" to "您好"
    )
}