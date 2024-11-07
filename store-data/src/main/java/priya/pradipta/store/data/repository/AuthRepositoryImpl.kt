package priya.pradipta.store.data.repository

import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.datasource.AuthRemoteDatasource
import priya.pradipta.store.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDatasource: AuthRemoteDatasource,
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String,
    ): ResultOf<Unit> =
        try {
            authRemoteDatasource.login(username, password)
            ResultOf.Success(Unit)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }
}
