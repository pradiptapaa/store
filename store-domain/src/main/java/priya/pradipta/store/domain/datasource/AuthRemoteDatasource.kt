package priya.pradipta.store.domain.datasource

interface AuthRemoteDatasource {
    suspend fun login(
        username: String,
        password: String,
    )
}
