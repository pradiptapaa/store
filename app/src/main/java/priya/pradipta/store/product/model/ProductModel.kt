package priya.pradipta.store.product.model

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
    val image: String? = null,
    val category: String = "",
    val rating: String = "",
    val count: String = "",
    val description: String = "",
) {
    fun getCapitalizeCategory(): String = category.capitalize(Locale.current)
}