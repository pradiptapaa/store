package priya.pradipta.store.data.datasource

import priya.pradipta.store.data.room.CartDao
import priya.pradipta.store.data.room.CartEntity
import priya.pradipta.store.domain.datasource.StoreLocalDatasource
import priya.pradipta.store.domain.entity.Cart

class StoreLocalImpl(
    private val dao: CartDao,
) : StoreLocalDatasource {
    override suspend fun saveToCart(cart: Cart) {
        dao.saveToCarts(
            cartEntity =
                CartEntity(
                    image = cart.image,
                    name = cart.name,
                    price = cart.price,
                    quantity = cart.quantity,
                ),
        )
    }

    override suspend fun getCarts(): List<Cart> {
        val carts = dao.getAllCarts()
        return carts.map {
            Cart(
                id = it.id,
                image = it.image,
                name = it.name,
                price = it.price,
                quantity = it.quantity,
            )
        }
    }
}
