package priya.pradipta.store.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import priya.pradipta.store.domain.datasource.AuthRemoteDatasource

@Serializable
data class AuthLoginBody(
    val username: String,
    val password: String,
)

class AuthKtorImpl(
    private val client: HttpClient,
) : AuthRemoteDatasource {
    override suspend fun login(
        username: String,
        password: String,
    ) {
        client
            .post("https://fakestoreapi.com/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(AuthLoginBody(username, password))
            }.body<Unit>()
    }
}
