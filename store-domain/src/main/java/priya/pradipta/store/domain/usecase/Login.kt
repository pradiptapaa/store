package priya.pradipta.store.domain.usecase

import priya.pradipta.store.domain.repository.AuthRepository

class Login(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(
        username: String,
        password: String,
    ) = repository.login(username, password)
}
