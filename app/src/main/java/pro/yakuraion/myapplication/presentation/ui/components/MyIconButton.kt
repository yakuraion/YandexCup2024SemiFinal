package pro.yakuraion.myapplication.presentation.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun MyIconButton(
    painter: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    active: Boolean = false,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = when {
                !enabled -> MaterialTheme.colorScheme.secondary
                active -> MaterialTheme.colorScheme.tertiary
                else -> MaterialTheme.colorScheme.primary
            }
        )
    }
}
