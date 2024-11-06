package priya.pradipta.store.product.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import priya.pradipta.store.product.list.item.ProductListItem

data class Product(
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

sealed class ProductListUIState {
    data object Loading : ProductListUIState()

    data class OnSuccess(
        val products: List<Product>,
    ) : ProductListUIState()

    data class OnFailure(
        val message: String,
    ) : ProductListUIState()
}

@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    products: List<Product> =
        listOf(
            Product(
                name = "Lon Sellers",
                price = "9100",
                quantity = "21",
                image = null,
                category = "Man Clothes",
                rating = "antiopam",
            ),
            Product(
                name = "Lucile Calderon",
                price = "appetere",
                quantity = "diam",
                image = null,
                category = "himenaeos",
                rating = "phasellus",
            ),
        ),
) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            ProductListItem(product = product)
            BaseGap(8.dp)
        }
    }
}

@Composable
fun BaseGap(
    gap: Dp,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = modifier.size(gap))
}

@Preview
@Composable
private fun ProductListScreenPreview() {
    ProductListScreen()
}
