package priya.pradipta.store.data.repository

import priya.pradipta.common.ResultOf
import priya.pradipta.store.domain.datasource.StoreLocalDatasource
import priya.pradipta.store.domain.datasource.StoreRemoteDatasource
import priya.pradipta.store.domain.entity.Cart
import priya.pradipta.store.domain.entity.Product
import priya.pradipta.store.domain.repository.StoreRepository

class StoreRepositoryImpl(
    private val storeRemoteDatasource: StoreRemoteDatasource,
    private val storeLocalDatasource: StoreLocalDatasource,
) : StoreRepository {
    override suspend fun getProducts(): ResultOf<List<Product>> =
        try {
            val result = storeRemoteDatasource.getSProduct()
            ResultOf.Success(result)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }

    override suspend fun saveToCart(cart: Cart): ResultOf<Unit> =
        try {
            val result = storeLocalDatasource.saveToCart(cart)
            ResultOf.Success(result)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }

    override suspend fun getCarts(): ResultOf<List<Cart>> =
        try {
            val result = storeLocalDatasource.getCarts()
            ResultOf.Success(result)
        } catch (e: Exception) {
            ResultOf.Failure(e)
        }
}
