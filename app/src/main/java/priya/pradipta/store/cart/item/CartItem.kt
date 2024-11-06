package priya.pradipta.store.cart.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import priya.pradipta.store.component.BaseImageRemote
import priya.pradipta.store.product.list.BaseGap

data class Cart(
    val image: String? = null,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    cart: Cart = Cart(image = null, name = "Errol Hart", price = "1000", quantity = "commune"),
) {
    val defaultTextModifier = Modifier.fillMaxWidth()
    Row(
        modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
    ) {
        BaseImageRemote(
            url = cart.image,
            modifier =
                Modifier
                    .fillMaxHeight()
                    .aspectRatio(0.8f),
        )
        BaseGap(16.dp)
        Column {
            Row {
                Text(
                    text = cart.name,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = defaultTextModifier.weight(1f),
                )
                BaseGap(8.dp)
                BaseFilledIconButton(imageVector = Icons.Filled.Close)
            }
            BaseGap(8.dp)
            Row {
                Text(
                    text = cart.price,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = defaultTextModifier.weight(1f).align(Alignment.CenterVertically),
                    color = MaterialTheme.colorScheme.secondary,
                )
                QuantityItem(quantityDefault = "0")
            }
        }
    }
}

@Composable
fun QuantityItem(
    modifier: Modifier = Modifier,
    quantityDefault: String = "",
) {
    val quantity = remember { mutableIntStateOf(quantityDefault.toInt()) }

    Row(modifier = modifier) {
        BaseFilledIconButton(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onClick = { if (quantity.intValue > 0) quantity.intValue-- },
            modifier = Modifier.wrapContentSize(),
        )
        BaseGap(8.dp)
        Text(
            text = quantity.intValue.toString(),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.secondary,
        )
        BaseGap(8.dp)
        BaseFilledIconButton(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            onClick = { quantity.intValue++ },
            modifier = Modifier.wrapContentHeight(),
        )
    }
}

@Composable
fun BaseFilledIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    FilledIconButton(onClick = onClick, modifier = modifier, shape = RoundedCornerShape(4.dp)) {
        Icon(imageVector, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondary)
    }
}

@Composable
fun BaseIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(imageVector, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
    }
}

@Preview(showBackground = true)
@Composable
private fun CartItemPreview() {
    CartItem(
        cart =
            Cart(
                image = "https://example.com/image.jpg",
                name = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
                price = "100",
                quantity = "9",
            ),
    )
}
