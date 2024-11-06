package priya.pradipta.store.domain.datasource

import priya.pradipta.store.domain.entity.Product

interface StoreRemoteDatasource {
    suspend fun getSProduct(): List<Product>
}
