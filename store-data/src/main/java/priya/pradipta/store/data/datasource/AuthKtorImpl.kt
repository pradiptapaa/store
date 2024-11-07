package priya.pradipta.store.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import priya.pradipta.store.domain.datasource.AuthRemoteDatasource

class AuthKtorImpl : AuthRemoteDatasource {
    private val client = HttpClient()

    override suspend fun login(
        username: String,
        password: String,
    ) {
        client
            .post("https://fakestoreapi.com/auth/login") {
                setBody(
                    """
                    {
                        "username": "$username",
                        "password": "$password"
                    }
                    """.trimIndent(),
                )
            }.body<Unit>()
    }
}
