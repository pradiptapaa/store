package priya.pradipta.store.domain.repository

import priya.pradipta.common.ResultOf

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String,
    ): ResultOf<Unit>
}
