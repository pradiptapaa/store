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
                    category = cart.category,
                    description = cart.description,
                    price = cart.price,
                    rating = cart.rating,
                    count = cart.count,
                ),
        )
    }

    override suspend fun getCarts(): List<Cart> {
        val carts = dao.getAllCarts()
        return carts.map {
            Cart(
                image = it.image,
                name = it.name,
                category = it.category,
                description = it.description,
                price = it.price,
                rating = it.rating,
                count = it.count,
            )
        }
    }
}
