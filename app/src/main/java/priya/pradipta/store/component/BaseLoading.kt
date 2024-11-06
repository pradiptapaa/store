package priya.pradipta.store.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BaseLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview
@Composable
private fun BaseLoadingPreview() {
    BaseLoading()
}
