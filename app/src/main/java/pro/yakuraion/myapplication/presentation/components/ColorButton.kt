package pro.yakuraion.myapplication.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.core.applyIf

@Composable
fun ColorButton(
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    withBorder: Boolean = false,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Spacer(
            modifier = Modifier
                .size(32.dp)
                .background(color, CircleShape)
                .applyIf(withBorder) {
                    border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = CircleShape)
                }
        )
    }
}
