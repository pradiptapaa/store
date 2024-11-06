package priya.pradipta.store.domain.repository

import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.entity.Product

interface StoreRepository {
    suspend fun getProducts(): List<Product>

    suspend fun saveToCart(cart: Cart)

    suspend fun getCarts(): List<Cart>
}
