package priya.pradipta.store.domain.repository

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String,
    )
}
