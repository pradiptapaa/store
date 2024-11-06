package priya.pradipta.store.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun BaseImageRemote(
    modifier: Modifier = Modifier,
    url: String? = null,
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = null,
    )
}
