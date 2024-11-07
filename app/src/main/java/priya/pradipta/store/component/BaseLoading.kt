package priya.pradipta.store.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun BaseLoading(modifier: Modifier = Modifier) {
    Dialog(onDismissRequest = {}) {
        val shape = CircleShape
        Surface(
            shape = shape,
            modifier = modifier.size(64.dp),
            shadowElevation = 2.dp,
            color = MaterialTheme.colorScheme.background,
        ) {
            CircularProgressIndicator(
                modifier = modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Preview
@Composable
private fun BaseLoadingPreview() {
    BaseLoading()
}
