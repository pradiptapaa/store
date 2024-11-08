package priya.pradipta.store.domain.entity

data class Cart(
    val id: Long = 0,
    val image: String? = null,
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val price: String = "",
    val quantity: String = "",
    val rating: String = "",
    val count: String = ""
)
