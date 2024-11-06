package priya.pradipta.store.data.datasource

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import priya.pradipta.store.domain.datasource.StoreRemoteDatasource
import priya.pradipta.store.domain.entity.Product

class StoreKtorImpl : StoreRemoteDatasource {
    private val client = HttpClient()

    override suspend fun getSProduct(): List<Product> = client.get("https://fakestoreapi.com/products").body()
}
