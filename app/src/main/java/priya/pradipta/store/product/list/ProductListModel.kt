package priya.pradipta.store.product.list

import kotlinx.serialization.Serializable
import priya.pradipta.store.product.model.ProductModel

@Serializable
data class ProductListModel(
    val products: List<ProductModel> = listOf()
)