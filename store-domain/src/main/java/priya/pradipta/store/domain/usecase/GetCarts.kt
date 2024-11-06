package priya.pradipta.store.domain.usecase

import priya.pradipta.store.domain.repository.StoreRepository

class GetCarts(
    private val repository: StoreRepository,
) {
    suspend operator fun invoke() = repository.getCarts()
}
