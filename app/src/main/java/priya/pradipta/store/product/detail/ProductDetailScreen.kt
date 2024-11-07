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
import androidx.compose.ui.unit.dp
import priya.pradipta.store.common.PreviewTheme
import priya.pradipta.store.component.BaseImageRemote
import priya.pradipta.store.component.BaseLoading
import priya.pradipta.store.product.list.BaseGap
import priya.pradipta.store.product.list.item.ProductHeaderDetail
import priya.pradipta.store.product.model.ProductModel

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
    product: ProductModel =
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
) {
    Column(modifier = modifier) {
        BaseImageRemote(url = product.image, modifier = Modifier.fillMaxWidth().aspectRatio(1.5f))
        Column( modifier = Modifier.padding(8.dp)) {
            ProductHeaderDetail(product = product, titleMaxLines = Int.MAX_VALUE)
            BaseGap(16.dp)
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@PreviewTheme
@Composable
private fun ProductDetailContentPreview() {
    ProductDetailContent()
}
