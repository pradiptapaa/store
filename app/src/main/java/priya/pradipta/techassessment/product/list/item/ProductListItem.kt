package priya.pradipta.techassessment.product.list.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import priya.pradipta.techassessment.component.BaseImageRemote
import priya.pradipta.techassessment.product.list.Product

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    product: Product = Product(),
) {
    val defaultTextModifier = Modifier.fillMaxWidth()
    Row(modifier = modifier.height(IntrinsicSize.Max).fillMaxWidth()) {
        BaseImageRemote(url = product.image, modifier = Modifier.fillMaxHeight().aspectRatio(1f))
        Column {
            // name
            Text(text = product.name, modifier = defaultTextModifier)
            // price
            Text(text = product.price, modifier = defaultTextModifier)
            // description
            Text(text = product.getCapitalizeCategory(), modifier = defaultTextModifier)
            // rating
            Text(text = product.rating, modifier = defaultTextModifier)
        }
    }
}

@Preview
@Composable
private fun ProductListItemPreview() {
    ProductListItem(
        product =
            Product(
                name = "Product 1",
                image = "https://example.com/image1.jpg",
                price = "$100",
                category = "Category 1",
                rating = "4.5",
            ),
    )
}
