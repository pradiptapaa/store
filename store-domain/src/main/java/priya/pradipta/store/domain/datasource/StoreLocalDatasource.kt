package priya.pradipta.store.domain.datasource

import priya.pradipta.store.domain.entity.Cart

interface StoreLocalDatasource {
    suspend fun saveToCart(cart: Cart)

    suspend fun getCarts(): List<Cart>
}
