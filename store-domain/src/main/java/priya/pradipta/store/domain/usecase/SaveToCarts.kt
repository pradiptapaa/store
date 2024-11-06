package priya.pradipta.store.domain.usecase

import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.repository.StoreRepository

class SaveToCarts(
    private val repository: StoreRepository,
) {
    suspend operator fun invoke(cart: Cart) = repository.saveToCart(cart)
}
