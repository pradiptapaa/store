package priya.pradipta.store.product.list

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import priya.pradipta.store.common.PreviewTheme
import priya.pradipta.store.common.toast
import priya.pradipta.store.component.BaseLoading
import priya.pradipta.store.product.list.item.ProductListItem
import priya.pradipta.store.product.model.ProductModel

sealed class ProductListUIState {
    data object Initial : ProductListUIState()

    data object Loading : ProductListUIState()

    data class OnSuccess(
        val products: List<ProductModel>,
    ) : ProductListUIState()

    data class OnFailure(
        val message: String,
    ) : ProductListUIState()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    productListUIState: ProductListUIState = ProductListUIState.Initial,
    onClick: (ProductModel) -> Unit = {},
    title: String = "Product List"
) {
    val context = LocalContext.current
    Log.d("Teeeeeeeeest", "ProductListScreen: $productListUIState")
    Scaffold(topBar = { TopAppBar(title = { Text(title) }) }) { innerPadding ->
        if (productListUIState is ProductListUIState.Loading) {
            BaseLoading()
        }
        if (productListUIState is ProductListUIState.OnSuccess) {
            ProductListContent(
                products = productListUIState.products,
                onClick = onClick,
                modifier = Modifier.padding(innerPadding),
            )
        }
        if (productListUIState is ProductListUIState.OnFailure) {
            toast(context, productListUIState.message)
        }
    }
}

@Composable
fun ProductListContent(
    modifier: Modifier = Modifier,
    products: List<ProductModel> = listOf(),
    onClick: (ProductModel) -> Unit = {},
) {
    LazyColumn(modifier = modifier) {
        items(products) { product ->
            ProductListItem(product = product, onClick = { onClick(product) })
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

@PreviewTheme
@Composable
private fun ProductListScreenPreview() {
    ProductListContent(
        products =
            listOf(
                ProductModel(
                    name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = "109.95",
                    quantity = "eirmod",
                    image = null,
                    category = "Man's clothes",
                    rating = "3.9",
                    count = "120",
                    description = "omittam",
                ),
                ProductModel(
                    name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                    price = "109.95",
                    quantity = "eirmod",
                    image = null,
                    category = "Man's clothes",
                    rating = "3.9",
                    count = "120",
                    description = "omittam",
                ),
            ),
    )
}
