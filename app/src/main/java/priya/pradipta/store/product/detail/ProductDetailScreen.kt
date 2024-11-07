package priya.pradipta.store.product.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import priya.pradipta.store.cart.item.BaseFilledIconButton
import priya.pradipta.store.common.PreviewTheme
import priya.pradipta.store.common.toast
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
    data object Initial : ProductDetailUIState()

    data object Loading : ProductDetailUIState()

    data class OnDetailSuccess(
        val detail: ProductDetail,
    ) : ProductDetailUIState()

    data class OnDetailFailure(
        val message: String,
    ) : ProductDetailUIState()

    data object OnSaveToCartSuccess : ProductDetailUIState()

    data class OnSaveToCartFailure(
        val message: String,
    ) : ProductDetailUIState()
}

@Composable
fun ProductDetailScreen(
    product: ProductModel = ProductModel(),
    productDetailState: ProductDetailUIState = ProductDetailUIState.Initial,
    onClick: () -> Unit = {},
) {
    var isCartShow by rememberSaveable { mutableStateOf(true) }
    if (productDetailState is ProductDetailUIState.Loading) {
        BaseLoading()
    }
    if (productDetailState is ProductDetailUIState.OnSaveToCartSuccess) {
        isCartShow = false
    }
    val context = LocalContext.current
    if (productDetailState is ProductDetailUIState.OnSaveToCartFailure) {
        toast(context = context, message = productDetailState.message)
    }
    ProductDetailContent(
        product = product,
        onClick = onClick,
        isCartShow = isCartShow,
    )
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
    isCartShow: Boolean = true,
    onClick: () -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            BaseGap(8.dp)
            BaseImageRemote(
                url = product.image,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.5f),
            )
            Column(modifier = Modifier.padding(8.dp)) {
                ProductHeaderDetail(product = product, titleMaxLines = Int.MAX_VALUE)
                BaseGap(16.dp)
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                )
            }
        }
        if (isCartShow) {
            BaseFilledIconButton(
                imageVector = Icons.Filled.ShoppingCart,
                modifier = Modifier.align(Alignment.BottomEnd),
            )
        }
        BaseFilledIconButton(
            imageVector = Icons.Filled.ArrowBackIosNew,
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onClick,
        )
    }
}

@PreviewTheme
@Composable
private fun ProductDetailContentPreview() {
    ProductDetailContent()
}
