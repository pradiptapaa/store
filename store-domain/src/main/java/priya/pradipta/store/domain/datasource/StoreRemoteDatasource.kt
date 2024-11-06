package priya.pradipta.store.domain.datasource

import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.entity.Product

interface StoreRemoteDatasource {
    suspend fun getSProduct(): List<Product>

    suspend fun saveToCart(cart: Cart)

    suspend fun getCarts(): List<Cart>
}
