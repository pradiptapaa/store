package priya.pradipta.store.domain.repository

import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.entity.Product

interface StoreRepository {
    suspend fun getProducts(): ResultOf<List<Product>>

    suspend fun saveToCart(cart: Cart): ResultOf<Unit>

    suspend fun getCarts(): ResultOf<List<Cart>>
}
