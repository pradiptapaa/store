package priya.pradipta.store.product.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import priya.pradipta.store.component.BaseImageRemote
import priya.pradipta.store.component.BaseLoading
import priya.pradipta.store.product.list.BaseGap
import priya.pradipta.store.product.list.Product

data class ProductDetail(
    val name: String,
    val price: Double,
    val description: String,
)

sealed class ProductDetailUIState {
    data object Loading : ProductDetailUIState()

    data class OnSuccess(
        val detail: ProductDetail,
    ) : ProductDetailUIState()

    data class OnFailure(
        val message: String,
    ) : ProductDetailUIState()
}

@Composable
fun ProductDetailScreen(
    productId: String,
    productDetailState: ProductDetailUIState,
) {
    if (productDetailState is ProductDetailUIState.Loading) {
        BaseLoading()
    }
}

@Composable
fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: Product =
        Product(
            name = "Fred Nelson",
            price = "invidunt",
            quantity = "mentitum",
            image = null,
            category = "habeo",
            rating = "lacus",
            description = "bibendum",
        ),
) {
    Column(modifier = modifier) {
        BaseImageRemote(url = product.image, modifier = Modifier.fillMaxWidth().aspectRatio(1.5f))
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column {
                Text(
                    product.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    product.price,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                Row {
                    Text(
                        product.rating,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.wrapContentWidth(),
                    )
                    BaseGap(8.dp)
                    Text(
                        product.count,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                BaseGap(8.dp)
            }
            Text(
                product.description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize().weight(1f),
            )
        }
    }
}

@Preview
@Composable
private fun ProductDetailContentPreview() {
    ProductDetailContent()
}
