package priya.pradipta.store.product.list.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import priya.pradipta.store.common.PreviewTheme
import priya.pradipta.store.component.BaseImageRemote
import priya.pradipta.store.product.list.BaseGap
import priya.pradipta.store.product.model.ProductModel

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    product: ProductModel = ProductModel(),
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable { },
    ) {
        BaseGap(8.dp)
        Row {
            BaseImageRemote(url = product.image, modifier = Modifier.size(160.dp))
            BaseGap(8.dp)
            ProductHeaderDetail(product = product)
        }
        BaseGap(8.dp)
    }
}

@Composable
fun ProductHeaderDetail(
    modifier: Modifier = Modifier,
    product: ProductModel = ProductModel(),
    titleMaxLines: Int = 1,
) {
    val defaultTextModifier = Modifier.fillMaxWidth()

    Column(modifier = modifier) {
        // name
        Text(
            text = product.name,
            modifier = defaultTextModifier,
            maxLines = titleMaxLines,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
        )
        BaseGap(2.dp)
        Text(
            text = product.getCapitalizeCategory(),
            modifier = defaultTextModifier,
            style = MaterialTheme.typography.labelMedium,
        )
        BaseGap(4.dp)
        // price
        Text(
            text = product.price,
            modifier = defaultTextModifier,
            style = MaterialTheme.typography.labelMedium,
        )
        BaseGap(2.dp)
        // rating
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Outlined.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryContainer,
            )
            BaseGap(4.dp)
            Text(
                text = product.rating,
                style = MaterialTheme.typography.labelMedium,
            )
            BaseGap(4.dp)
            Text(
                text = "(${product.count})",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@PreviewTheme
@Composable
private fun ProductListItemPreview() {
    ProductListItem(
        product =
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
    )
}
