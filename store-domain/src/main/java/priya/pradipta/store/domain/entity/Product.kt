package priya.pradipta.store.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id") var id: Int? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("price") var price: Double? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("category") var category: String? = null,
    @SerialName("image") var image: String? = null,
    @SerialName("rating") var rating: Rating? = Rating(),
)
