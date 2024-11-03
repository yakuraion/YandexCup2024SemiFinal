package pro.yakuraion.myapplication.presentation.screens.drawing.components.working.bottommenu

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import pro.yakuraion.myapplication.presentation.components.ColorButton

private val COLORS = listOf(
    Color.White,
    Color.Red,
    Color.Black,
    Color.Blue,
)

@Composable
fun ColorsBottomMenu(
    currentColor: Color,
    onColorClick: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomMenu(
        modifier = modifier
            .padding(horizontal = 12.dp),
    ) {
        COLORS.forEachIndexed { index, color ->
            if (index != 0) {
                Spacer(Modifier.width(8.dp))
            }

            ColorButton(
                color = color,
                onClick = { onColorClick(color) }
            )
        }
    }
}
